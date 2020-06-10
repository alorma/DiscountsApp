class Ticket {
  final String id;
  final String store;
  final String conditions;
  final String discountAmount;
  final String discountType;
  final String barcodeType;
  final String barcodeCode;
  final DateTime expireDate;

  Ticket({
    this.id,
    this.store,
    this.conditions,
    this.discountAmount,
    this.discountType,
    this.barcodeType,
    this.barcodeCode,
    this.expireDate,
  });

  factory Ticket.fromJson(Map<String, dynamic> json) {
    var date = DateTime.fromMillisecondsSinceEpoch(
      json['expireDate']['_seconds'] * 1000,
    );
    return Ticket(
      id: json['id'],
      store: json['store'],
      conditions: json['conditions'],
      discountAmount: json['discountAmount'],
      discountType: json['discountType'],
      barcodeType: json['barcodeType'],
      barcodeCode: json['barcodeCode'],
      expireDate: date
    );
  }
}
