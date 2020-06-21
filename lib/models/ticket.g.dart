// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'ticket.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Ticket _$TicketFromJson(Map<String, dynamic> json) {
  return Ticket()
    ..id = json['id'] as String
    ..store = json['store'] as String
    ..conditions = json['conditions'] as String
    ..discountAmount = json['discountAmount'] as String
    ..discountType = json['discountType'] as String
    ..barcodeType = json['barcodeType'] as String
    ..barcodeCode = json['barcodeCode'] as String
    ..expireDate = json['expireDate'] == null
        ? null
        : DateTime.parse(json['expireDate'] as String);
}

Map<String, dynamic> _$TicketToJson(Ticket instance) => <String, dynamic>{
      'id': instance.id,
      'store': instance.store,
      'conditions': instance.conditions,
      'discountAmount': instance.discountAmount,
      'discountType': instance.discountType,
      'barcodeType': instance.barcodeType,
      'barcodeCode': instance.barcodeCode,
      'expireDate': instance.expireDate?.toIso8601String(),
    };
