package org.jss.tally.builder;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jss.tally.domain.Ledger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

@Component
public class RequestBuilder {
    private VelocityEngine velocityEngine;

    @Autowired
    public RequestBuilder(VelocityEngine velocityEngine){
        this.velocityEngine = velocityEngine;
    }

    public String buildNewLedgerRequest(Ledger ledger) {
        Template template = velocityEngine.getTemplate("/request/template/new_patient_ledger.vm");
        VelocityContext context = new VelocityContext();
        context.put("patientname", ledger.getPatientName());
        context.put("patientid", ledger.getPatientId());
        context.put("company", ledger.getCompany());

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }
}
