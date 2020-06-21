import 'package:app/models/ticket.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter/foundation.dart';

abstract class TicketState extends Equatable {
  const TicketState();

  @override
  List<Object> get props => [];
}

class Initial extends TicketState {}

class Loading extends TicketState {}

class AllTicketsLoaded extends TicketState {
  final List<Ticket> tickets;

  const AllTicketsLoaded({@required this.tickets});

  @override
  List<Object> get props => [tickets];

  @override
  bool get stringify => true;
}

class Failure extends TicketState {
  final String error;

  const Failure({@required this.error});

  @override
  List<Object> get props => [error];

  @override
  bool get stringify => true;
}
