package com.sithagi.countrycodepicker;

public interface CountryPickerListener {
	public void onSelectCountry(String name, String code, String dialCode, String currencySymbol, String currency, String flagDrawableName);
}
