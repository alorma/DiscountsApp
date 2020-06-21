import 'package:equatable/equatable.dart';

abstract class TicketEvent extends Equatable {
  const TicketEvent();

  @override
  List<Object> get props => [];
}

class AllTicketsRequested extends TicketEvent {
  final String groupId;

  AllTicketsRequested(this.groupId);
}
