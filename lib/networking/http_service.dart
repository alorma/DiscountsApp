import 'dart:convert';
import 'dart:io';

import 'package:app/networking/http_exceptions.dart';
import 'package:app/networking/serializable.dart';
import 'package:http/http.dart' as http;

class HttpService<T, S extends Serializable<T>> {
  final S _serializable;

  HttpService(this._serializable);
  final _baseUrl = "https://us-central1-discounts-31e10.cloudfunctions.net/widgets";

  Future<T> get(String path) async {
    var responseObject;
    try {
      final response = await http.get(_baseUrl + path);
      responseObject = _processResponse(response);
    } catch (e) {
      _throwSpecificException(e);
    }
    return responseObject;
  }

  Future<List<T>> getAll(String path) async {
    var responseObject;
    try {
      final response = await http.get(_baseUrl + path);
      responseObject = _processResponseArray(response);
    } catch (e) {
      _throwSpecificException(e);
    }
    return responseObject;
  }

  Future<T> post(String path, T t) async {
    var responseObject;
    try {
      final response = await http.post(_baseUrl + path,
          body: jsonEncode(_serializable.toJson(t)),
          headers: _getDefaultHeaders());
      responseObject = _processResponse(response);
    } catch (e) {
      _throwSpecificException(e);
    }
    return responseObject;
  }

  Future<T> put(String path, T t) async {
    var responseObject;
    try {
      final response = await http.put(_baseUrl + path,
          body: jsonEncode(_serializable.toJson(t)),
          headers: _getDefaultHeaders());
      responseObject = _processResponse(response);
    } catch (e) {
      _throwSpecificException(e);
    }
    return responseObject;
  }

  Future<bool> delete(String path) async {
    var result;
    try {
      final response = await http.delete(_baseUrl + path);
      result = response.statusCode == 200;
    } catch (e) {
      _throwSpecificException(e);
    }
    return result;
  }

  List<T> _processResponseArray(http.Response response) {
    var responseList;
    if (_isSuccessOrThrow(response)) {
      responseList =
          _serializable.fromJsonArray(jsonDecode(response.body.toString()));
    }
    return responseList;
  }

  T _processResponse(http.Response response) {
    var responseObject;
    if (_isSuccessOrThrow(response)) {
      responseObject =
          _serializable.fromJson(jsonDecode(response.body.toString()));
    }
    return responseObject;
  }

  bool _isSuccessOrThrow(http.Response response) {
    switch (response.statusCode) {
      case 200:
      case 201:
        return true;
      case 400:
        throw BadRequestException(response.body.toString());
      case 401:
      case 403:
        throw UnauthorisedException(response.body.toString());
      case 404:
        throw ResourceNotFoundException(response.body.toString());
      case 500:
      default:
        throw FetchDataException(
            'Error occurred while communicating with server : ${response.statusCode}');
    }
  }

  _throwSpecificException(Exception e) {
    if (e is FormatException) {
      throw BadUrlException(e.message);
    } else if (e is SocketException) {
      throw FetchDataException('No Internet connection');
    } else
      throw e;
  }

  Map<String, String> _getDefaultHeaders() {
    return {"content-type": "application/json"};
  }
}
