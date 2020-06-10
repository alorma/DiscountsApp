class Ticket {
  final String id;
  final String store;
  final String barcodeCode;
  final String barcodeType;

  Ticket({this.id, this.barcodeCode, this.barcodeType, this.store});

  factory Ticket.fromJson(Map<String, dynamic> json) {
    return Ticket(
      id: json['id'],
      barcodeCode: json['barcodeCode'],
      barcodeType: json['barcodeType'],
      store: json['store'],
    );
  }
}