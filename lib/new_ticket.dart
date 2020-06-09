import 'package:barcode_scan/barcode_scan.dart';
import 'package:flutter/material.dart';

class CreateTicketScreen extends StatefulWidget {
  CreateTicketScreen({Key key}) : super(key: key);

  @override
  CreateTicketScreenState createState() => CreateTicketScreenState();
}

class CreateTicketScreenState extends State<CreateTicketScreen> {
  ScanResult _scanResult;

  void _scanBarcode() async {
    var result = await BarcodeScanner.scan();
    setState(() {
      _scanResult = result;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Create ticket"),
        leading: IconButton(
            icon: Icon(Icons.arrow_back),
            onPressed: () {
              Navigator.pop(context);
            }),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            _scanResult != null
                ? scanResultWidget(context)
                : RaisedButton(
                    onPressed: _scanBarcode,
                    child: Text("Scan"),
                  ),
          ],
        ),
      ),
    );
  }

  Column scanResultWidget(BuildContext context) {
    return Column(
      children: <Widget>[
        Text('Type: ${_scanResult.type}'),
        Text('Raw: ${_scanResult.rawContent}'),
        Text('Format: ${_scanResult.format}'),
        Text('Format note: ${_scanResult.formatNote}'),
      ],
    );
  }
}
