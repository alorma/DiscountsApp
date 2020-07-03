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
        return createTicketCard(ctx, ticket, discountSymbol, dateFormat);
      },
    );
  }

  Widget createTicketCard(
    BuildContext context,
    Ticket ticket,
    discountSymbol,
    DateFormat dateFormat,
  ) {
    return Card(
      shape: ticketItemShape(),
      child: InkWell(
        borderRadius: ticketItemShapeRadius(),
        onTap: () {
          openTicketDetail(context, ticket);
        },
        child: detailCardContent(ticket, discountSymbol, dateFormat),
      ),
      margin: EdgeInsets.all(4),
    );
  }

  ShapeBorder ticketItemShape() {
    return RoundedRectangleBorder(borderRadius: ticketItemShapeRadius());
  }

  BorderRadius ticketItemShapeRadius() =>
      BorderRadius.only(topRight: Radius.circular(10));

  Padding detailCardContent(
      Ticket ticket, discountSymbol, DateFormat dateFormat) {
    return Padding(
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
    );
  }

  void openTicketDetail(BuildContext context, Ticket ticket) {
    showModalBottomSheet(
      context: context,
      shape: detailShape(),
      builder: (builderContext) => buildDetailBottomSheet(builderContext),
    );
  }

  ShapeBorder detailShape() {
    return RoundedRectangleBorder(
      borderRadius: detailShapeRadius(),
    );
  }

  BorderRadius detailShapeRadius() {
    return BorderRadius.only(
        topLeft: Radius.circular(10),
        topRight: Radius.circular(10),
      );
  }

  Text _ticketsError(String error) => Text("error");

  Widget buildDetailBottomSheet(BuildContext builderContext) {
    return Container(
      child: Wrap(
        children: <Widget>[
          ListTile(
            leading: Icon(Icons.photo_camera),
            title: Text('Camera'),
            onTap: () {},
          ),
          ListTile(
            leading: Icon(Icons.photo_library),
            title: Text('Select'),
            onTap: () {},
          ),
          ListTile(
            leading: Icon(Icons.delete),
            title: Text('Delete'),
            onTap: () {},
          ),
          Divider(),
          if (true)
            ListTile(
              title: Text('Cancel'),
              onTap: () {
                Navigator.pop(context);
              },
            ),
        ],
      ),
    );
  }
}
