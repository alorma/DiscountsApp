import 'package:json_annotation/json_annotation.dart';

part 'ticket.g.dart';

@JsonSerializable()
class Ticket {
  String id;
  String store;
  String conditions;
  String discountAmount;
  String discountType;
  String barcodeType;
  String barcodeCode;
  DateTime expireDate;

  Ticket();

  factory Ticket.fromJson(Map<String, dynamic> json) => _$TicketFromJson(json);

  Map<String, dynamic> toJson() => _$TicketToJson(this);
}
