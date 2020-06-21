import 'package:app/bloc/ticket/ticket_event.dart';
import 'package:app/bloc/ticket/ticket_state.dart';
import 'package:app/models/ticket.dart';
import 'package:app/repository/ticket_repository.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class TicketBloc extends Bloc<TicketEvent, TicketState> {
  TicketRepository _ticketRepository;

  TicketBloc() {
    _ticketRepository = TicketRepository();
  }

  @override
  TicketState get initialState => Initial();

  @override
  Stream<TicketState> mapEventToState(TicketEvent event) async* {
    if (event is AllTicketsRequested) {
      yield Loading();

      try {
        List<Ticket> allTickets = await _ticketRepository.getAll(event.groupId);
        yield AllTicketsLoaded(tickets: allTickets);
      } catch (error) {
        yield Failure(error: error.toString());
      }
    }
  }
}
