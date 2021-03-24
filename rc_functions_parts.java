	public static double round_double (double d)
	{
		double value = d;
	    value = Math.round(100.0 * value) / 100.0; // Ergebnis: 1.23
	    return value;
	}
	public static BigDecimal getbiggerbigdecimal (BigDecimal eins, BigDecimal zwei, BigDecimal wert_wenn_eins_groesser_zwei,
			BigDecimal wert_wenn_zwei_groesser_eins)
		{
	
		BigDecimal bigdecimalnull = new BigDecimal ("0.00000000");
		int result1 = eins.compareTo(zwei);
		if (result1  == 1  ) 
		{
			return wert_wenn_eins_groesser_zwei;
		}
		else {
			return wert_wenn_zwei_groesser_eins;
		}
		
		}
	
	public static BigDecimal slidervaluetobigdecimal(double min) {
		BigDecimal minusvier = new BigDecimal("-4.00000000");
		BigDecimal minusdrei = new BigDecimal("-3.00000000");
		BigDecimal minuszwei = new BigDecimal("-2.00000000");
		BigDecimal minuseins = new BigDecimal("-1.00000000");
		BigDecimal bigdecimalnull = new BigDecimal("0.00000000");
		BigDecimal pluseins = new BigDecimal("1.00000000");
		BigDecimal pluszwei = new BigDecimal("2.00000000");
		BigDecimal plusdrei = new BigDecimal("3.00000000");
		BigDecimal plusvier = new BigDecimal("4.00000000");

		BigDecimal returnvalue = new BigDecimal ("0.00000000");
		
		if (min == -4 || min == -4.0)
		{returnvalue = minusvier;}
		if (min == -3 || min == -3.0)
		{returnvalue = minusdrei;}
		if (min == -2 || min == -2.0)
		{returnvalue = minuszwei;}
		if (min == -1 || min == -1.0)
		{returnvalue=  minuseins;}
		if (min == 0 || min == 0.0)
		{returnvalue =  bigdecimalnull;}
		
		if (min == 4 || min == 4.0)
		{returnvalue = plusvier;}
		if (min == 3 || min == 3.0)
		{returnvalue =  plusdrei;}
		if (min == 2 || min == 2.0)
		{returnvalue =  pluszwei;}
		if (min == 1 || min == 1.0)
		{returnvalue =  pluseins;}
		
		return returnvalue;
	
	}

	public static double round_double_onedigit(double bps_au_min_final_rounded) {
		BigDecimal bd = new BigDecimal(bps_au_min_final_rounded).setScale(1, RoundingMode.HALF_EVEN);
		double d = bd.doubleValue();
		return d;
	}
