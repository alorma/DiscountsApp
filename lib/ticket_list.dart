import 'package:app/bloc/ticket/ticket_bloc.dart';
import 'package:app/bloc/ticket/ticket_event.dart';
import 'package:app/bloc/ticket/ticket_state.dart';
import 'package:app/icons/icons.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:intl/intl.dart';

import 'models/ticket.dart';

class TicketListScreen extends StatefulWidget {
  TicketListScreen({Key key, this.title}) : super(key: key);

  final String title;

  @override
  TicketListScreenState createState() => TicketListScreenState();
}

class TicketListScreenState extends State<TicketListScreen> {
  @override
  void initState() {
    BlocProvider.of<TicketBloc>(context)
        .add(AllTicketsRequested("PRmjpHIMw60GhKXmviDy"));
    super.initState();
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
    return BlocBuilder<TicketBloc, TicketState>(
      builder: (context, ticketState) {
        if (ticketState is AllTicketsLoaded) {
          return _ticketsContent(ticketState.tickets);
        } else if (ticketState is Failure) {
          return _ticketsError((ticketState).error);
        } else {
          return CircularProgressIndicator();
        }
      },
    );
  }

  ListView _ticketsContent(List<Ticket> tickets) {
    return ListView.builder(
      itemCount: tickets.length,
      itemBuilder: (ctx, index) {
        var ticket = tickets[index];
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

  Text _ticketsError(String error) => Text("error");
}
