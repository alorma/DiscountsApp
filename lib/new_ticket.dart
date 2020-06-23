import 'package:barcode_scan/barcode_scan.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:intl/intl.dart';

enum DiscountType { discount, amount }

class CreateTicketScreen extends StatefulWidget {
  CreateTicketScreen({Key key}) : super(key: key);

  @override
  CreateTicketScreenState createState() => CreateTicketScreenState();
}

class CreateTicketScreenState extends State<CreateTicketScreen> {
  DiscountType _discountType;
  DateTime selectedDate = DateTime.now();
  final _formKey = GlobalKey<FormState>();
  ScanResult _scanResult;

  var codeTextController = TextEditingController();
  var dateTextController = TextEditingController();
  var amountTextController = TextEditingController();

  @override
  void initState() {
    dateTextController.text = "";
    codeTextController.text = "";
    amountTextController.text = "";
    super.initState();
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
      floatingActionButton: Align(
        alignment: Alignment.bottomCenter,
        child: FloatingActionButton.extended(
          onPressed: () {
            submitForm();
          },
          label: Text("Save"),
        ),
      ),
      body: Builder(
        builder: (BuildContext context) => buildForm(context),
      ),
    );
  }

  Form buildForm(BuildContext context) {
    return Form(
      key: _formKey,
      child: Padding(
        padding: EdgeInsets.all(8),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            buildFormSpace(8),
            buildTitleTextField(),
            buildFormSpace(16),
            buildBarcodeTextField(context),
            buildFormSpace(8),
            Row(
              children: <Widget>[
                Expanded(
                  child: buildAmountTextField(context),
                ),
                Expanded(
                  child: buildAmountTypeField(context),
                ),
              ],
            ),
            buildFormSpace(16),
            buildExpireTextField(context),
          ],
        ),
      ),
    );
  }

  void submitForm() {
    if (_formKey.currentState.validate()) {
      if (_scanResult != null) {}
    }
  }

  Widget buildFormSpace(double space) {
    return SizedBox(height: space);
  }

  Widget buildTitleTextField() {
    return TextFormField(
      decoration: InputDecoration(
        labelText: "Title",
        border: OutlineInputBorder(),
      ),
      validator: (value) {
        if (value.isEmpty) {
          return 'Please enter some text';
        }
        return null;
      },
    );
  }

  Widget buildAmountTextField(BuildContext context) {
    var label = "Select an option";
    if (_discountType == DiscountType.amount) {
      label = "Amount";
    } else if (_discountType == DiscountType.discount) {
      label = "Discount";
    }

    var suffix;
    if (_discountType == DiscountType.amount) {
      suffix = "€";
    } else if (_discountType == DiscountType.discount) {
      suffix = "%";
    }

    var enabled = _discountType != null;

    return TextFormField(
      controller: amountTextController,
      decoration: InputDecoration(
        labelText: label,
        border: OutlineInputBorder(),
        suffixText: suffix,
      ),
      textAlign: TextAlign.end,
      keyboardType: TextInputType.number,
      inputFormatters: <TextInputFormatter>[
        WhitelistingTextInputFormatter.digitsOnly,
      ],
      enableInteractiveSelection: enabled,
      onTap: () {
        if (!enabled) {
          FocusScope.of(context).requestFocus(new FocusNode());
        }
      },
      validator: (value) {
        if (value.isEmpty) {
          return 'Please enter value';
        }
        return null;
      },
    );
  }

  Widget buildAmountTypeField(BuildContext context) {
    return Column(
      children: <Widget>[
        ListTile(
          title: const Text('Amount'),
          leading: Radio(
            value: DiscountType.amount,
            groupValue: _discountType,
            onChanged: (DiscountType value) {
              setState(() {
                _discountType = value;
              });
            },
          ),
        ),
        ListTile(
          title: const Text('Discount'),
          leading: Radio(
            value: DiscountType.discount,
            groupValue: _discountType,
            onChanged: (DiscountType value) {
              setState(() {
                _discountType = value;
              });
            },
          ),
        ),
      ],
    );
  }

  Widget buildExpireTextField(BuildContext context) {
    return TextFormField(
      controller: dateTextController,
      decoration: InputDecoration(
        labelText: "Expire date",
        border: OutlineInputBorder(),
      ),
      enableInteractiveSelection: false,
      onTap: () {
        FocusScope.of(context).requestFocus(new FocusNode());
        selectDate(context);
      },
      validator: (value) {
        if (value.isEmpty) {
          return 'Please enter date';
        }
        return null;
      },
    );
  }

  Widget buildBarcodeTextField(BuildContext context) {
    return TextFormField(
      controller: codeTextController,
      decoration: InputDecoration(
        labelText: "Code",
        border: OutlineInputBorder(),
        suffixText: buildScanResultType(),
      ),
      enableInteractiveSelection: false,
      onTap: () {
        FocusScope.of(context).requestFocus(new FocusNode());
        scanBarcode();
      },
      validator: (value) {
        if (value.isEmpty) {
          return 'Please enter barcode';
        }
        return null;
      },
    );
  }

  String buildScanResultType() {
    if (_scanResult != null) {
      return _scanResult.format.toString();
    } else {
      return "";
    }
  }

  String formatSelectedDate() =>
      DateFormat('yyyy / MM / dd').format(selectedDate);

  selectDate(BuildContext context) async {
    DateTime selected = await showDatePicker(
      context: context,
      initialDate: selectedDate,
      firstDate: selectedDate.subtract(new Duration(days: 365)),
      lastDate: selectedDate.add(new Duration(days: 365)),
    );
    if (selected != null) {
      setState(() {
        selectedDate = selected;
        dateTextController.text = formatSelectedDate();
      });
    }
  }

  void scanBarcode() async {
    var result = await BarcodeScanner.scan();
    setState(() {
      _scanResult = result;
      codeTextController.text = _scanResult.rawContent;
    });
  }

/*
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
   */
}
