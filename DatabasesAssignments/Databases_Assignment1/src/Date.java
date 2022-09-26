public class Date
{
    private String symbol;
    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;
    private double adjusted;
    private int split;
    private double tOpen;

    public Date(String symbol, String date, double open, double high, double low, double close, long volume, double adjusted)
    {
        this.symbol=symbol;
        this.date=date;
        this.open=open;
        this.high=high;
        this.low=low;
        this.close=close;
        this.volume=volume;
        this.adjusted=adjusted;
        this.split=0;
        this.tOpen=0;
    }

    public  Date(Date input)
    {
        this.symbol=input.symbol;
        this.date=input.date;
        this.open=input.open;
        this.high=input.high;
        this.low=input.low;
        this.close=input.close;
        this.volume=input.volume;
        this.adjusted=input.adjusted;
        this.split=input.split;
        this.tOpen=input.tOpen;
    }

    public double crazyDay()
    {
        return ((high-low)/high);
    }

    public int splitcheck(Date tomorrow)
    {
        //System.out.println(symbol+"::"+tomorrow.symbol);
        //System.out.println(date+"##"+tomorrow.date);
        //System.out.println(close+"//"+tomorrow.open);
        tOpen=tomorrow.open;
        if(Math.abs(close/tomorrow.open-2.0)<.05)
        {
            split=2;
            return 2;
        }
        else if(Math.abs(close/tomorrow.open-3.0)<.05)
        {
            split=3;
            return 3;
        }
        else if(Math.abs(close/tomorrow.open-1.5)<.05)
        {
            split=4;
            return 4;
        }
        else
        {
            return 1;
        }
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void end()
    {
        symbol="END";
    }


    public String getDate() {
        return date;
    }

    public int getSplit() {
        return split;
    }

    public double getClose() {
        return close;
    }

    public double gettOpen() {
        return tOpen;
    }
}
