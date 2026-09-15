import org.junit.jupiter.api.Test;

public class GeneratedRangeLengthRandoopMetamorphicPassingTest {

    @Test
    public void testShape001() {
        java.time.LocalDate localDate10 = java.time.LocalDate.now();
        java.time.LocalDate localDate12 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate10);
        org.threeten.extra.LocalDateRange localDateRange15 = org.threeten.extra.LocalDateRange.ofEmpty(localDate12);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input16 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange15);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input16);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input16);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape002() {
        java.time.LocalDate localDate0 = java.time.LocalDate.now();
        java.time.LocalDate localDate7 = java.time.LocalDate.now();
        org.threeten.extra.LocalDateRange localDateRange27 = org.threeten.extra.LocalDateRange.of(localDate0, localDate7);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input28 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange27);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input28);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input28);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape003() {
        byte byte0 = (byte)100;
        int int1 = 1;
        byte byte2 = (byte)1;
        java.time.LocalDate localDate3 = java.time.LocalDate.of((int) (byte)100, 1, (int) (byte)1);
        java.time.LocalDate localDate4 = java.time.LocalDate.now();
        org.threeten.extra.LocalDateRange localDateRange24 = org.threeten.extra.LocalDateRange.ofClosed(localDate3, localDate4);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input25 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange24);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input25);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input25);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape004() {
        byte byte0 = (byte)0;
        short short1 = (short)1;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay((int) (byte)0, (int) (short)1);
        java.time.LocalDate localDate3 = java.time.LocalDate.EPOCH;
        java.time.LocalDate localDate4 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate3);
        org.threeten.extra.LocalDateRange localDateRange5 = org.threeten.extra.LocalDateRange.ofClosed(localDate2, localDate4);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input6 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange5);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input6);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input6);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape005() {
        char char4 = '#';
        java.time.LocalDate localDate5 = java.time.LocalDate.ofEpochDay((long) '#');
        org.threeten.extra.LocalDateRange localDateRange8 = org.threeten.extra.LocalDateRange.ofUnboundedStart(localDate5);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input9 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange8);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input9);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input9);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape006() {
        long long0 = 0L;
        java.time.LocalDate localDate1 = java.time.LocalDate.ofEpochDay(0L);
        org.threeten.extra.LocalDateRange localDateRange3 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate1);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input4 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange3);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input4);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input4);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape007() {
        byte byte1 = (byte)100;
        int int2 = 1;
        java.time.LocalDate localDate3 = java.time.LocalDate.ofYearDay((int) (byte)100, 1);
        org.threeten.extra.LocalDateRange localDateRange10 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate3);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input11 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange10);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input11);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input11);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape008() {
        short short0 = (short)0;
        short short1 = (short)100;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay((int) (short)0, (int) (short)100);
        org.threeten.extra.LocalDateRange localDateRange3 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate2);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input4 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange3);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input4);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input4);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape009() {
        long long0 = 10L;
        java.time.LocalDate localDate1 = java.time.LocalDate.ofEpochDay(10L);
        org.threeten.extra.LocalDateRange localDateRange5 = org.threeten.extra.LocalDateRange.ofEmpty(localDate1);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input6 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange5);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input6);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input6);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape010() {
        char char4 = '#';
        java.time.LocalDate localDate5 = java.time.LocalDate.ofEpochDay((long) '#');
        int int8 = 0;
        java.time.Period period9 = java.time.Period.ofWeeks(0);
        org.threeten.extra.LocalDateRange localDateRange10 = org.threeten.extra.LocalDateRange.of(localDate5, period9);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input11 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange10);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input11);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input11);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape011() {
        long long22 = 100L;
        java.time.LocalDate localDate23 = java.time.LocalDate.ofEpochDay(100L);
        java.time.LocalDate localDate24 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate23);
        java.time.LocalDate localDate45 = java.time.LocalDate.now();
        java.time.LocalDate localDate47 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate45);
        org.threeten.extra.LocalDateRange localDateRange64 = org.threeten.extra.LocalDateRange.of(localDate24, localDate47);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input65 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange64);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input65);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input65);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape012() {
        java.time.LocalDate localDate3 = java.time.LocalDate.now();
        java.time.LocalDate localDate5 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate3);
        org.threeten.extra.LocalDateRange localDateRange7 = org.threeten.extra.LocalDateRange.ofUnboundedStart(localDate5);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input8 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange7);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input8);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input8);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape013() {
        char char0 = '#';
        byte byte1 = (byte)1;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay((int) '#', (int) (byte)1);
        java.time.LocalDate localDate4 = java.time.LocalDate.now();
        org.threeten.extra.LocalDateRange localDateRange32 = org.threeten.extra.LocalDateRange.of(localDate2, localDate4);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input33 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange32);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input33);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input33);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape014() {
        java.time.LocalDate localDate0 = java.time.LocalDate.now();
        org.threeten.extra.LocalDateRange localDateRange47 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate0);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input48 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange47);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input48);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input48);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape015() {
        int int0 = 10;
        int int1 = 10;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay(10, 10);
        java.time.LocalDate localDate3 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate2);
        java.time.LocalDate localDate26 = java.time.LocalDate.now();
        org.threeten.extra.LocalDateRange localDateRange69 = org.threeten.extra.LocalDateRange.ofClosed(localDate3, localDate26);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input70 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange69);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input70);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input70);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape016() {
        int int0 = 10;
        byte byte1 = (byte)1;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay(10, (int) (byte)1);
        org.threeten.extra.LocalDateRange localDateRange4 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate2);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input5 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange4);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input5);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input5);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape017() {
        char char0 = ' ';
        java.time.LocalDate localDate1 = java.time.LocalDate.ofEpochDay((long) ' ');
        java.time.LocalDate localDate2 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate1);
        org.threeten.extra.LocalDateRange localDateRange3 = org.threeten.extra.LocalDateRange.ofEmpty(localDate2);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input4 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange3);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input4);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input4);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape018() {
        int int0 = 10;
        byte byte1 = (byte)1;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay(10, (int) (byte)1);
        short short51 = (short)100;
        java.time.Period period52 = java.time.Period.ofDays((int) (short)100);
        org.threeten.extra.LocalDateRange localDateRange55 = org.threeten.extra.LocalDateRange.of(localDate2, period52);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input56 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange55);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input56);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input56);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape019() {
        java.time.LocalDate localDate3 = java.time.LocalDate.now();
        java.time.LocalDate localDate5 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate3);
        byte byte20 = (byte)1;
        char char21 = '#';
        char char22 = 'a';
        java.time.Period period23 = java.time.Period.of((int) (byte)1, (int) '#', (int) 'a');
        org.threeten.extra.LocalDateRange localDateRange24 = org.threeten.extra.LocalDateRange.of(localDate5, period23);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input25 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange24);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input25);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input25);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape020() {
        char char0 = 'a';
        int int1 = 10;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay((int) 'a', 10);
        byte byte72 = (byte)1;
        java.time.Period period73 = java.time.Period.ofDays((int) (byte)1);
        org.threeten.extra.LocalDateRange localDateRange74 = org.threeten.extra.LocalDateRange.of(localDate2, period73);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input75 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange74);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input75);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input75);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape021() {
        long long0 = 10L;
        java.time.LocalDate localDate1 = java.time.LocalDate.ofEpochDay(10L);
        org.threeten.extra.LocalDateRange localDateRange2 = org.threeten.extra.LocalDateRange.ofUnboundedStart(localDate1);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input3 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange2);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input3);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input3);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape022() {
        long long0 = 100L;
        java.time.LocalDate localDate1 = java.time.LocalDate.ofEpochDay(100L);
        java.time.LocalDate localDate2 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate1);
        java.time.LocalDate localDate12 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate2);
        org.threeten.extra.LocalDateRange localDateRange14 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate12);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input15 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange14);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input15);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input15);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape023() {
        int int0 = 10;
        byte byte1 = (byte)1;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay(10, (int) (byte)1);
        org.threeten.extra.LocalDateRange localDateRange86 = org.threeten.extra.LocalDateRange.ofUnboundedStart(localDate2);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input87 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange86);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input87);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input87);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape024() {
        char char4 = '#';
        java.time.LocalDate localDate5 = java.time.LocalDate.ofEpochDay((long) '#');
        org.threeten.extra.LocalDateRange localDateRange13 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate5);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input14 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange13);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input14);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input14);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape025() {
        byte byte1 = (byte)100;
        int int2 = 1;
        java.time.LocalDate localDate3 = java.time.LocalDate.ofYearDay((int) (byte)100, 1);
        java.time.LocalDate localDate5 = java.time.LocalDate.now();
        java.time.LocalDate localDate8 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate5);
        org.threeten.extra.LocalDateRange localDateRange9 = org.threeten.extra.LocalDateRange.of(localDate3, localDate8);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input10 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange9);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input10);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input10);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape026() {
        java.time.LocalDate localDate29 = java.time.LocalDate.EPOCH;
        java.time.LocalDate localDate30 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate29);
        org.threeten.extra.LocalDateRange localDateRange35 = org.threeten.extra.LocalDateRange.ofUnboundedStart(localDate30);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input36 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange35);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input36);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input36);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape027() {
        java.time.LocalDate localDate0 = java.time.LocalDate.now();
        int int37 = 1;
        java.time.LocalDate localDate38 = java.time.LocalDate.ofEpochDay((long) 1);
        java.time.LocalDate localDate40 = java.time.LocalDate.now();
        java.time.Period period41 = java.time.Period.between(localDate38, localDate40);
        java.time.Period period42 = java.time.Period.from((java.time.temporal.TemporalAmount) period41);
        org.threeten.extra.LocalDateRange localDateRange43 = org.threeten.extra.LocalDateRange.of(localDate0, period42);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input44 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange43);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input44);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input44);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape028() {
        java.time.LocalDate localDate10 = java.time.LocalDate.now();
        java.time.LocalDate localDate12 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate10);
        long long15 = 100L;
        java.time.LocalDate localDate16 = java.time.LocalDate.ofEpochDay(100L);
        java.time.LocalDate localDate17 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate16);
        java.time.LocalDate localDate27 = java.time.LocalDate.now();
        java.time.Period period37 = java.time.Period.between(localDate17, localDate27);
        org.threeten.extra.LocalDateRange localDateRange38 = org.threeten.extra.LocalDateRange.of(localDate12, period37);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input39 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange38);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input39);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input39);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape029() {
        short short3 = (short)0;
        java.time.LocalDate localDate4 = java.time.LocalDate.ofEpochDay((long) (short)0);
        char char16 = '#';
        java.time.LocalDate localDate17 = java.time.LocalDate.ofEpochDay((long) '#');
        org.threeten.extra.LocalDateRange localDateRange30 = org.threeten.extra.LocalDateRange.of(localDate4, localDate17);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input31 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange30);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input31);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input31);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape030() {
        char char0 = '#';
        byte byte1 = (byte)1;
        short short2 = (short)10;
        java.time.LocalDate localDate3 = java.time.LocalDate.of((int) '#', (int) (byte)1, (int) (short)10);
        org.threeten.extra.LocalDateRange localDateRange4 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate3);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input5 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange4);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input5);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input5);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape031() {
        char char0 = 'a';
        int int1 = 10;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay((int) 'a', 10);
        org.threeten.extra.LocalDateRange localDateRange12 = org.threeten.extra.LocalDateRange.ofUnboundedStart(localDate2);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input13 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange12);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input13);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input13);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape032() {
        byte byte14 = (byte)100;
        int int15 = 100;
        java.time.LocalDate localDate16 = java.time.LocalDate.ofYearDay((int) (byte)100, 100);
        org.threeten.extra.LocalDateRange localDateRange18 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate16);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input19 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange18);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input19);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input19);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape033() {
        byte byte1 = (byte)100;
        int int2 = 1;
        java.time.LocalDate localDate3 = java.time.LocalDate.ofYearDay((int) (byte)100, 1);
        char char11 = '#';
        java.time.LocalDate localDate12 = java.time.LocalDate.ofEpochDay((long) '#');
        org.threeten.extra.LocalDateRange localDateRange13 = org.threeten.extra.LocalDateRange.ofClosed(localDate3, localDate12);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input14 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange13);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input14);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input14);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape034() {
        char char0 = '4';
        short short1 = (short)1;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay((int) '4', (int) (short)1);
        char char3 = '#';
        java.time.Period period4 = java.time.Period.ofWeeks((int) '#');
        java.time.Period period6 = java.time.Period.from((java.time.temporal.TemporalAmount) period4);
        org.threeten.extra.LocalDateRange localDateRange7 = org.threeten.extra.LocalDateRange.of(localDate2, period6);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input8 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange7);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input8);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input8);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape035() {
        short short0 = (short)-1;
        java.time.LocalDate localDate1 = java.time.LocalDate.ofEpochDay((long) (short)-1);
        org.threeten.extra.LocalDateRange localDateRange4 = org.threeten.extra.LocalDateRange.ofUnboundedStart(localDate1);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input5 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange4);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input5);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input5);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape036() {
        byte byte0 = (byte)10;
        char char1 = 'a';
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay((int) (byte)10, (int) 'a');
        org.threeten.extra.LocalDateRange localDateRange3 = org.threeten.extra.LocalDateRange.ofUnboundedStart(localDate2);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input4 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange3);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input4);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input4);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape037() {
        char char0 = '#';
        int int1 = 100;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay((int) '#', 100);
        org.threeten.extra.LocalDateRange localDateRange3 = org.threeten.extra.LocalDateRange.ofEmpty(localDate2);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input4 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange3);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input4);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input4);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape038() {
        java.time.LocalDate localDate4 = java.time.LocalDate.now();
        char char50 = '4';
        char char51 = 'a';
        short short52 = (short)10;
        java.time.Period period53 = java.time.Period.of((int) '4', (int) 'a', (int) (short)10);
        org.threeten.extra.LocalDateRange localDateRange54 = org.threeten.extra.LocalDateRange.of(localDate4, period53);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input55 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange54);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input55);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input55);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape039() {
        long long0 = 1L;
        java.time.LocalDate localDate1 = java.time.LocalDate.ofEpochDay(1L);
        org.threeten.extra.LocalDateRange localDateRange3 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate1);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input4 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange3);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input4);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input4);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape040() {
        int int0 = 1;
        short short1 = (short)1;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay(1, (int) (short)1);
        java.time.LocalDate localDate51 = java.time.LocalDate.now();
        java.time.LocalDate localDate53 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate51);
        org.threeten.extra.LocalDateRange localDateRange92 = org.threeten.extra.LocalDateRange.of(localDate2, localDate53);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input93 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange92);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input93);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input93);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape041() {
        short short0 = (short)1;
        int int1 = 100;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay((int) (short)1, 100);
        java.time.LocalDate localDate4 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate2);
        org.threeten.extra.LocalDateRange localDateRange5 = org.threeten.extra.LocalDateRange.ofEmpty(localDate4);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input6 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange5);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input6);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input6);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape042() {
        byte byte0 = (byte)10;
        int int1 = 3;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay((int) (byte)10, 3);
        java.time.LocalDate localDate3 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate2);
        java.time.LocalDate localDate39 = java.time.LocalDate.now();
        java.time.LocalDate localDate41 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate39);
        org.threeten.extra.LocalDateRange localDateRange48 = org.threeten.extra.LocalDateRange.of(localDate3, localDate41);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input49 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange48);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input49);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input49);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape043() {
        java.time.LocalDate localDate23 = java.time.LocalDate.now();
        java.time.LocalDate localDate26 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate23);
        char char29 = '#';
        java.time.Period period30 = java.time.Period.ofWeeks((int) '#');
        org.threeten.extra.LocalDateRange localDateRange31 = org.threeten.extra.LocalDateRange.of(localDate26, period30);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input32 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange31);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input32);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input32);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape044() {
        long long0 = 100L;
        java.time.LocalDate localDate1 = java.time.LocalDate.ofEpochDay(100L);
        java.time.LocalDate localDate3 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate1);
        org.threeten.extra.LocalDateRange localDateRange9 = org.threeten.extra.LocalDateRange.ofUnboundedStart(localDate3);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input10 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange9);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input10);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input10);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape045() {
        long long0 = 100L;
        java.time.LocalDate localDate1 = java.time.LocalDate.ofEpochDay(100L);
        java.time.LocalDate localDate2 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate1);
        java.time.LocalDate localDate12 = java.time.LocalDate.from((java.time.temporal.TemporalAccessor) localDate2);
        org.threeten.extra.LocalDateRange localDateRange13 = org.threeten.extra.LocalDateRange.ofEmpty(localDate12);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input14 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange13);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input14);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input14);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape046() {
        int int0 = 3;
        java.time.LocalDate localDate1 = java.time.LocalDate.ofEpochDay((long) 3);
        org.threeten.extra.LocalDateRange localDateRange2 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate1);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input3 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange2);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input3);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input3);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape047() {
        java.time.LocalDate localDate15 = java.time.LocalDate.now();
        short short23 = (short)0;
        java.time.LocalDate localDate24 = java.time.LocalDate.ofEpochDay((long) (short)0);
        char char37 = '#';
        java.time.LocalDate localDate38 = java.time.LocalDate.ofEpochDay((long) '#');
        java.time.Period period45 = java.time.Period.between(localDate24, localDate38);
        org.threeten.extra.LocalDateRange localDateRange46 = org.threeten.extra.LocalDateRange.of(localDate15, period45);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input47 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange46);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input47);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input47);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape048() {
        int int0 = 10;
        byte byte1 = (byte)1;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay(10, (int) (byte)1);
        org.threeten.extra.LocalDateRange localDateRange5 = org.threeten.extra.LocalDateRange.ofEmpty(localDate2);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input6 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange5);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input6);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input6);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape049() {
        int int2 = 0;
        java.time.LocalDate localDate3 = java.time.LocalDate.ofEpochDay((long) 0);
        org.threeten.extra.LocalDateRange localDateRange5 = org.threeten.extra.LocalDateRange.ofEmpty(localDate3);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input6 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange5);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input6);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input6);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape050() {
        short short0 = (short)10;
        short short1 = (short)10;
        java.time.LocalDate localDate2 = java.time.LocalDate.ofYearDay((int) (short)10, (int) (short)10);
        org.threeten.extra.LocalDateRange localDateRange26 = org.threeten.extra.LocalDateRange.from((java.time.temporal.Temporal) localDate2);
        MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input input27 = new MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.Input(localDateRange26);
        var followUp = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.generateFollowUp(input27);
        var sourceOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(input27);
        var followUpOutput = MtllmGeneratedLocalDateRangeLengthInDaysInvocation71f3tr.invoke(followUp);
        RangeLengthMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
