import 'package:app/new_ticket.dart';
import 'package:app/ticket_list.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Discounts',
      initialRoute: '/',
      routes: {
        '/': (context) => TicketListScreen(title: 'Discounts at your palm'),
        '/newTicket': (context) => CreateTicketScreen()
      },
      theme: ThemeData(
        primarySwatch: Colors.green,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
    );
  }
}
