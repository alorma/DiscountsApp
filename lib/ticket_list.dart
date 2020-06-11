import 'dart:convert';

import 'package:app/icons/icons.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:intl/intl.dart';

import 'models/ticket.dart';

class TicketListScreen extends StatefulWidget {
  TicketListScreen({Key key, this.title}) : super(key: key);

  final String title;

  @override
  TicketListScreenState createState() => TicketListScreenState();
}

class TicketListScreenState extends State<TicketListScreen> {
  Future<List<Ticket>> futureTickets;

  @override
  void initState() {
    super.initState();
    futureTickets = _fetchTickets('PRmjpHIMw60GhKXmviDy');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: _getList(),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.pushNamed(context, '/newTicket');
        },
        tooltip: 'Add',
        child: Icon(Icons.add),
      ),
    );
  }

  Widget _getList() {
    return FutureBuilder(
      future: futureTickets,
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          return _ticketsContent(snapshot);
        } else if (snapshot.hasError) {
          return _ticketsError(snapshot);
        }
        return CircularProgressIndicator();
      },
    );
  }

  ListView _ticketsContent(AsyncSnapshot snapshot) {
    return ListView.builder(
      itemCount: snapshot.data.length,
      itemBuilder: (ctx, index) {
        var ticket = snapshot.data[index] as Ticket;
        var dateFormat = DateFormat('dd-MM-yyyy');
        var discountSymbol;
        if (ticket.discountType == 'discount') {
          discountSymbol = CustomIcons.percent;
        } else {
          discountSymbol = Icons.euro_symbol;
        }
        return Card(
          child: Padding(
            padding: EdgeInsets.all(8),
            child: Column(
              children: <Widget>[
                Text(ticket.barcodeCode),
                Row(
                  children: <Widget>[
                    Icon(
                      discountSymbol,
                      size: 16,
                    ),
                    Text(ticket.discountAmount)
                  ],
                ),
                Row(
                  children: <Widget>[
                    Icon(
                      Icons.access_time,
                      size: 16,
                    ),
                    Text(dateFormat.format(ticket.expireDate))
                  ],
                )
              ],
            ),
          ),
          margin: EdgeInsets.all(4),
        );
      },
    );
  }

  Text _ticketsError(AsyncSnapshot snapshot) => Text("${snapshot.error}");

  Future<List<Ticket>> _fetchTickets(String groupId) async {
    return _doNetRequest(groupId)
        .then((response) => _parseResponse(response))
        .then((value) => _parseTickets(value))
        .catchError((e) {});
  }

  Future<http.Response> _doNetRequest(String groupId) {
    return http.get(
      'https://us-central1-discounts-31e10.cloudfunctions.net/widgets/group/$groupId/discounts',
    );
  }

  Future<dynamic> _parseResponse(http.Response response) async {
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      return List<Ticket>();
    }
  }

  Future<List<Ticket>> _parseTickets(data) async {
    List<Ticket> listModel = [];
    for (Map i in data) {
      listModel.add(Ticket.fromJson(i));
    }
    return listModel;
  }
}
