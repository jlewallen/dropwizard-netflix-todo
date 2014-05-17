﻿//http://www.adequatelygood.com/JavaScript-Module-Pattern-In-Depth.html
define([], function () {
	// Forces the JavaScript engine into strict mode: http://tinyurl.com/2dondlh
	//http://stackoverflow.com/questions/246801/how-can-you-encode-to-base64-using-javascript
	"use strict";

	return {
		_keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
		_utf8_encode: function (string) {

			var utftext = "", c, n;

			string = string.replace(/\r\n/g, "\n");

			for (n = 0; n < string.length; n++) {

				c = string.charCodeAt(n);

				if (c < 128) {

					utftext += String.fromCharCode(c);

				} else if ((c > 127) && (c < 2048)) {

					utftext += String.fromCharCode((c >> 6) | 192);
					utftext += String.fromCharCode((c & 63) | 128);

				} else {

					utftext += String.fromCharCode((c >> 12) | 224);
					utftext += String.fromCharCode(((c >> 6) & 63) | 128);
					utftext += String.fromCharCode((c & 63) | 128);

				}

			}

			return utftext;
		},
		_utf8_decode: function (utftext) {
			var string = "", i = 0, c = 0, c1 = 0, c2 = 0;

			while (i < utftext.length) {

				c = utftext.charCodeAt(i);

				if (c < 128) {

					string += String.fromCharCode(c);
					i++;

				} else if ((c > 191) && (c < 224)) {

					c1 = utftext.charCodeAt(i + 1);
					string += String.fromCharCode(((c & 31) << 6) | (c1 & 63));
					i += 2;

				} else {

					c1 = utftext.charCodeAt(i + 1);
					c2 = utftext.charCodeAt(i + 2);
					string += String.fromCharCode(((c & 15) << 12) | ((c1 & 63) << 6) | (c2 & 63));
					i += 3;

				}

			}

			return string;
		},
		_hexEncode: function (input) {
			var output = '', i;

			for (i = 0; i < input.length; i++) {
				output += input.charCodeAt(i).toString(16);
			}

			return output;
		},
		_hexDecode: function (input) {
			var output = '', i;

			if (input.length % 2 > 0) {
				input = '0' + input;
			}

			for (i = 0; i < input.length; i = i + 2) {
				output += String.fromCharCode(parseInt(input.charAt(i) + input.charAt(i + 1), 16));
			}

			return output;
		},
		encode: function (input) {
			var output = "", chr1, chr2, chr3, enc1, enc2, enc3, enc4, i = 0;

			input = this._utf8_encode(input);

			while (i < input.length) {

				chr1 = input.charCodeAt(i++);
				chr2 = input.charCodeAt(i++);
				chr3 = input.charCodeAt(i++);

				enc1 = chr1 >> 2;
				enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
				enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
				enc4 = chr3 & 63;

				if (isNaN(chr2)) {
					enc3 = enc4 = 64;
				} else if (isNaN(chr3)) {
					enc4 = 64;
				}

				output += this._keyStr.charAt(enc1);
				output += this._keyStr.charAt(enc2);
				output += this._keyStr.charAt(enc3);
				output += this._keyStr.charAt(enc4);

			}

			return output;
		},
		decode: function (input) {
			var output = "", chr1, chr2, chr3, enc1, enc2, enc3, enc4, i = 0;

			if (input != undefined) {
				input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

				while (i < input.length) {

					enc1 = this._keyStr.indexOf(input.charAt(i++));
					enc2 = this._keyStr.indexOf(input.charAt(i++));
					enc3 = this._keyStr.indexOf(input.charAt(i++));
					enc4 = this._keyStr.indexOf(input.charAt(i++));

					chr1 = (enc1 << 2) | (enc2 >> 4);
					chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
					chr3 = ((enc3 & 3) << 6) | enc4;

					output += String.fromCharCode(chr1);

					if (enc3 !== 64) {
						output += String.fromCharCode(chr2);
					}
					if (enc4 !== 64) {
						output += String.fromCharCode(chr3);
					}

				}
			}
			return this._utf8_decode(output);
		},
		decodeToHex: function (input) {
			return this._hexEncode(this.decode(input));
		},
		encodeFromHex: function (input) {
			return this.encode(this._hexDecode(input));
		}
		// TODO finish adding this. to the above and retest
	};
});
