zk.$package('com.softwarelikeyou.client.component.zul');
com.softwarelikeyou.client.component.zul.ImageLabel = zk.$extends(zul.LabelImageWidget, {

	_dir: "normal",
	_tabindex: -1,
	_multiline: false,
	_pre: false,

	$define: {		
		
		dir: _zkf = function () {
			this.updateDomContent_();
		},
		
		tabindex: function (v) {
			var n = this.$n();
			if (n) n.tabIndex = v < 0 ? '' : v;
		},
		
		multiline: function(v) { 
			var n = this.$n();
			if (n) n.multiline = v;
		},
		
		pre: function(v) { 
			var n = this.$n();
			if (n) n.pre = v;
		}
		
	},

	getEncodedText: function () {
		return zUtl.encodeXML(this._label, {
			multiline: this._multiline,
			pre: this._pre 
		});
	},
	
	getZclass: function(){
		var zcls = this._zclass;
		return zcls ? zcls : "z-imagelabel";
	},

	domAttrs_: function(no){
		
		var attr = this.$supers('domAttrs_', arguments), v;
		
		if (v = this.getTabindex()) 
			attr += ' tabIndex="' + v + '"';
		else 
			attr += ' href="javascript:;"';
		
		return attr;
	},
	
	doClick_: function(evt){
		if (this._disabled) {
			evt.stop();
		}
		else {
			this.fireX(evt);
			if (!evt.stopped) this.$super('doClick_', evt, true);
		}			
	}

});

