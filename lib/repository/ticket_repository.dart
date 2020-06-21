import 'package:app/models/ticket.dart';
import 'package:app/models/ticket_serializable.dart';
import 'package:app/networking/http_service.dart';

class TicketRepository {
  var _ticketSerializable;

  HttpService<Ticket, TicketSerializable> _ticketHttpService;

  TicketRepository() {
    _ticketSerializable = TicketSerializable();

    _ticketHttpService = HttpService(_ticketSerializable);
  }

  Future<List<Ticket>> getAll(String groupId) async {
    return _ticketHttpService.getAll("/group/$groupId/discounts");
  }
}
