class Ticket {
  final String id;
  final String store;
  final String code;

  Ticket({this.id, this.code, this.store});

  factory Ticket.fromJson(Map<String, dynamic> json) {
    return Ticket(
      id: json['id'],
      code: json['code'],
      store: json['store'],
    );
  }
}