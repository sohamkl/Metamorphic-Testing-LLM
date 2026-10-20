import org.junit.jupiter.api.Test;

public class GeneratedRenkoBarAggregatorMetamorphicPassingTest {

    @Test
    public void testShape001() {
        char char0 = '4';
        char[] charArray1 = new char[] { '4' };
        java.math.MathContext mathContext25 = java.math.MathContext.DECIMAL64;
        java.math.BigDecimal bigDecimal30 = new java.math.BigDecimal(charArray1, mathContext25);
        char char31 = '#';
        org.ta4j.core.aggregator.RenkoBarAggregator renkoBarAggregator32 = new org.ta4j.core.aggregator.RenkoBarAggregator((java.lang.Number)bigDecimal30, (int)'#');
        short short33 = (short)100;
        java.time.Duration duration34 = java.time.Duration.ofMillis((long) (short)100);
        java.time.Instant instant35 = null;
        int int36 = 7;
        java.time.Instant instant37 = java.time.Instant.ofEpochMilli((long) 7);
        org.ta4j.core.num.Num num38 = null;
        double double39 = 0.0d;
        java.math.MathContext mathContext41 = java.math.MathContext.DECIMAL64;
        org.ta4j.core.num.DecimalNum decimalNum43 = org.ta4j.core.num.DecimalNum.valueOf(0.0d, mathContext41);
        char char44 = '4';
        short short45 = (short)100;
        java.math.MathContext mathContext46 = new java.math.MathContext((int)(short)100);
        org.ta4j.core.num.DecimalNum decimalNum47 = org.ta4j.core.num.DecimalNum.valueOf((int) '4', mathContext46);
        short short49 = (short)0;
        org.ta4j.core.num.DecimalNum decimalNum52 = org.ta4j.core.num.DecimalNum.valueOf((java.lang.Number) (short)0);
        org.ta4j.core.num.Num num53 = null;
        org.ta4j.core.num.Num num54 = null;
        int int55 = 7;
        org.ta4j.core.BaseBar baseBar56 = new org.ta4j.core.BaseBar(duration34, instant35, instant37, num38, (org.ta4j.core.num.Num)decimalNum43, (org.ta4j.core.num.Num)decimalNum47, (org.ta4j.core.num.Num)decimalNum52, num53, num54, (long)7);
        MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f.Input input57 = new MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f.Input(renkoBarAggregator32, (org.ta4j.core.Bar)baseBar56);
        var followUp = MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f.generateFollowUp(input57);
        var sourceOutput = MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f.invoke(input57);
        var followUpOutput = MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f.invoke(followUp);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape002() {
        int int0 = 10;
        int int11 = 2;
        org.ta4j.core.aggregator.RenkoBarAggregator renkoBarAggregator12 = new org.ta4j.core.aggregator.RenkoBarAggregator((java.lang.Number)10, 2);
        short short13 = (short)100;
        java.time.Duration duration14 = java.time.Duration.ofMillis((long) (short)100);
        java.time.Instant instant15 = null;
        int int16 = 7;
        java.time.Instant instant17 = java.time.Instant.ofEpochMilli((long) 7);
        org.ta4j.core.num.Num num18 = null;
        double double19 = 0.0d;
        java.math.MathContext mathContext21 = java.math.MathContext.DECIMAL64;
        org.ta4j.core.num.DecimalNum decimalNum23 = org.ta4j.core.num.DecimalNum.valueOf(0.0d, mathContext21);
        char char24 = '4';
        short short25 = (short)100;
        java.math.MathContext mathContext26 = new java.math.MathContext((int)(short)100);
        org.ta4j.core.num.DecimalNum decimalNum27 = org.ta4j.core.num.DecimalNum.valueOf((int) '4', mathContext26);
        short short29 = (short)0;
        org.ta4j.core.num.DecimalNum decimalNum32 = org.ta4j.core.num.DecimalNum.valueOf((java.lang.Number) (short)0);
        org.ta4j.core.num.Num num33 = null;
        org.ta4j.core.num.Num num34 = null;
        int int35 = 7;
        org.ta4j.core.BaseBar baseBar36 = new org.ta4j.core.BaseBar(duration14, instant15, instant17, num18, (org.ta4j.core.num.Num)decimalNum23, (org.ta4j.core.num.Num)decimalNum27, (org.ta4j.core.num.Num)decimalNum32, num33, num34, (long)7);
        MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f.Input input37 = new MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f.Input(renkoBarAggregator12, (org.ta4j.core.Bar)baseBar36);
        var followUp = MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f.generateFollowUp(input37);
        var sourceOutput = MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f.invoke(input37);
        var followUpOutput = MtllmGeneratedRenkoBarAggregatorAggregateInvocation6lug9f.invoke(followUp);
        mtllm.examples.ta4j.RenkoBarAggregatorMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
