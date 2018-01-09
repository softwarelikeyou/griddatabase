zk.$package('com.softwarelikeyou.client.component.zul');
com.softwarelikeyou.client.component.zul.SimpleLabel = zk.$extends(zk.Widget, {
    _value : '', // default value

    getValue : function() {
        return this._value;
    },
    
    setValue : function(value) {
        if (this._value != value) {
            this._value = value;
            if (this.desktop)
                this.$n().innerHTML = zUtl.encodeXML(value);
        }
    }
});