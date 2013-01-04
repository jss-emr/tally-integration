package org.jss.tally.utils;

public class TallyResponseMother {
    public static String successfulTallyResponse() {
        return "<RESPONSE>\n" +
                "    <CREATED>0</CREATED>\n" +
                "    <ALTERED>1</ALTERED>\n" +
                "    <LASTVCHID>0</LASTVCHID>\n" +
                "    <LASTMID>0</LASTMID>\n" +
                "    <COMBINED>0</COMBINED>\n" +
                "    <IGNORED>0</IGNORED>\n" +
                "    <ERRORS>0</ERRORS>\n" +
                "</RESPONSE>";
    }

    public static String unsuccessfulTallyResponse() {
        return "<RESPONSE>\n" +
                "    <LINEERROR>Could not set &apos;SVCurrentCompany&apos; to &apos;JSS&apos;</LINEERROR>\n" +
                "</RESPONSE>";
    }
}
