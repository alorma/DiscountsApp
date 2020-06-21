import 'package:app/models/ticket.dart';
import 'package:app/networking/serializable.dart';

class TicketSerializable implements Serializable<Ticket> {
  @override
  Ticket fromJson(Map<String, dynamic> json) {
    return Ticket.fromJson(json);
  }

  @override
  Map<String, dynamic> toJson(Ticket ticket) {
    return ticket.toJson();
  }

  @override
  List<Ticket> fromJsonArray(List<dynamic> jsonArray) {
    return jsonArray
        ?.map((ticketMap) {
          return ticketMap == null ? null : fromJson(ticketMap);
        })
        ?.toList();
  }

  @override
  List<dynamic> toJsonArray(List<Ticket> ticketList) {
    return ticketList?.map((ticket) => ticket?.toJson())?.toList();
  }
}
