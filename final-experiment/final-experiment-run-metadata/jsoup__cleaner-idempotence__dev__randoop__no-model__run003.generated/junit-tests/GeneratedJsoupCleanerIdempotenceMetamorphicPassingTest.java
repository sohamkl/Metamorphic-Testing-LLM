import org.junit.jupiter.api.Test;

public class GeneratedJsoupCleanerIdempotenceMetamorphicPassingTest {

    @Test
    public void testShape001() {
        java.lang.String str2 = "hi!";
        java.lang.String str3 = "hi!";
        org.jsoup.safety.Safelist safelist8 = org.jsoup.safety.Safelist.simpleText();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input11 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("hi!", "hi!", safelist8);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input11);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input11);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape002() {
        java.lang.String str2 = "";
        java.lang.String str3 = "hi!";
        org.jsoup.safety.Safelist safelist5 = org.jsoup.safety.Safelist.basicWithImages();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input7 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("", "hi!", safelist5);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input7);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input7);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape003() {
        java.lang.String str0 = "hi!";
        java.lang.String str1 = "";
        org.jsoup.safety.Safelist safelist2 = org.jsoup.safety.Safelist.basic();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input3 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("hi!", "", safelist2);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input3);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input3);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape004() {
        java.lang.String str4 = "";
        java.lang.String str5 = "hi!";
        org.jsoup.safety.Safelist safelist6 = org.jsoup.safety.Safelist.relaxed();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input7 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("", "hi!", safelist6);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input7);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input7);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape005() {
        java.lang.String str2 = "";
        java.lang.String str3 = "";
        org.jsoup.safety.Safelist safelist6 = org.jsoup.safety.Safelist.basic();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input9 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("", "", safelist6);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input9);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input9);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape006() {
        java.lang.String str2 = "hi!";
        java.lang.String str3 = "";
        org.jsoup.safety.Safelist safelist6 = org.jsoup.safety.Safelist.simpleText();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input11 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("hi!", "", safelist6);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input11);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input11);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape007() {
        java.lang.String str2 = "hi!";
        java.lang.String str3 = "";
        org.jsoup.safety.Safelist safelist8 = org.jsoup.safety.Safelist.basicWithImages();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input12 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("hi!", "", safelist8);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input12);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input12);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape008() {
        java.lang.String str4 = "";
        java.lang.String str5 = "";
        org.jsoup.safety.Safelist safelist9 = org.jsoup.safety.Safelist.simpleText();
        org.jsoup.safety.Safelist safelist12 = new org.jsoup.safety.Safelist(safelist9);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input15 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("", "", safelist12);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input15);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input15);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape009() {
        java.lang.String str5 = "";
        java.lang.String str6 = "hi!";
        org.jsoup.safety.Safelist safelist12 = org.jsoup.safety.Safelist.simpleText();
        org.jsoup.safety.Safelist safelist15 = new org.jsoup.safety.Safelist(safelist12);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input19 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("", "hi!", safelist15);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input19);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input19);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape010() {
        java.lang.String str3 = "";
        java.lang.String str4 = "hi!";
        org.jsoup.safety.Safelist safelist11 = org.jsoup.safety.Safelist.basic();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input16 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("", "hi!", safelist11);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input16);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input16);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape011() {
        java.lang.String str2 = "";
        java.lang.String str3 = "";
        org.jsoup.safety.Safelist safelist8 = org.jsoup.safety.Safelist.relaxed();
        org.jsoup.safety.Safelist safelist9 = new org.jsoup.safety.Safelist(safelist8);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input13 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("", "", safelist9);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input13);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input13);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape012() {
        java.lang.String str2 = "hi!";
        java.lang.String str3 = "hi!";
        org.jsoup.safety.Safelist safelist7 = org.jsoup.safety.Safelist.basicWithImages();
        org.jsoup.safety.Safelist safelist10 = new org.jsoup.safety.Safelist(safelist7);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input11 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("hi!", "hi!", safelist10);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input11);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input11);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape013() {
        java.lang.String str5 = "hi!";
        java.lang.String str6 = "";
        org.jsoup.safety.Safelist safelist9 = org.jsoup.safety.Safelist.relaxed();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input11 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("hi!", "", safelist9);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input11);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input11);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape014() {
        java.lang.String str0 = "hi!";
        java.lang.String str1 = "hi!";
        org.jsoup.safety.Safelist safelist8 = org.jsoup.safety.Safelist.basic();
        org.jsoup.safety.Safelist safelist12 = new org.jsoup.safety.Safelist(safelist8);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input14 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("hi!", "hi!", safelist12);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input14);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input14);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape015() {
        java.lang.String str1 = "hi!";
        java.lang.String str2 = "";
        org.jsoup.safety.Safelist safelist5 = new org.jsoup.safety.Safelist();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input8 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("hi!", "", safelist5);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input8);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input8);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape016() {
        java.lang.String str0 = "hi!";
        java.lang.String str1 = "hi!";
        org.jsoup.safety.Safelist safelist11 = org.jsoup.safety.Safelist.relaxed();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input19 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("hi!", "hi!", safelist11);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input19);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input19);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape017() {
        java.lang.String str0 = "";
        java.lang.String str1 = "";
        org.jsoup.safety.Safelist safelist5 = org.jsoup.safety.Safelist.basicWithImages();
        org.jsoup.safety.Safelist safelist7 = new org.jsoup.safety.Safelist(safelist5);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input10 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("", "", safelist7);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input10);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input10);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape018() {
        java.lang.String str0 = "";
        java.lang.String str1 = "hi!";
        org.jsoup.safety.Safelist safelist4 = new org.jsoup.safety.Safelist();
        org.jsoup.safety.Safelist safelist5 = new org.jsoup.safety.Safelist(safelist4);
        org.jsoup.safety.Safelist safelist8 = new org.jsoup.safety.Safelist(safelist5);
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input10 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("", "hi!", safelist8);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input10);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input10);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape019() {
        java.lang.String str2 = "";
        java.lang.String str3 = "";
        org.jsoup.safety.Safelist safelist8 = new org.jsoup.safety.Safelist();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input11 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("", "", safelist8);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input11);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input11);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }

    @Test
    public void testShape020() {
        java.lang.String str0 = "hi!";
        java.lang.String str1 = "hi!";
        org.jsoup.safety.Safelist safelist8 = new org.jsoup.safety.Safelist();
        MtllmGeneratedJsoupCleanInvocationbsm8w3.Input input13 = new MtllmGeneratedJsoupCleanInvocationbsm8w3.Input("hi!", "hi!", safelist8);
        var followUp = MtllmGeneratedJsoupCleanInvocationbsm8w3.generateFollowUp(input13);
        var sourceOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(input13);
        var followUpOutput = MtllmGeneratedJsoupCleanInvocationbsm8w3.invoke(followUp);
        jsoupmt.CleanerIdempotenceMetamorphicSpec.assertRelation(sourceOutput, followUpOutput);
    }
}
