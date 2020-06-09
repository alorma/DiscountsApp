import 'package:barcode_scan/barcode_scan.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Discounts',
      theme: ThemeData(
        primarySwatch: Colors.green,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(title: 'Discounts at your palm'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
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
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            _scanResult != null
                ? scanResultWidget(context)
                : Text('Scan first ticket barcode'),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _scanBarcode,
        tooltip: 'Scan',
        child: Icon(Icons.scanner),
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
