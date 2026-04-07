package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.MedicalPanelUI;

public class PacienteVisibleQuestion implements Question<Boolean> {

    private static final String NO_PATIENT_LABEL = "Esperando paciente";
    private static final String NO_ACTIVE_ATTENTION_LABEL = "Aun no hay atencion activa";
    private static final String CALLED_PATIENT_PENDING_LABEL = "Paciente llamado pendiente de iniciar atencion";

    public static Question<Boolean> value() {
        return new PacienteVisibleQuestion();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String patientName = Text.of(MedicalPanelUI.NOMBRE_PACIENTE).answeredBy(actor).trim();
        String attentionStatus = Text.of(MedicalPanelUI.ESTADO_ATENCION_PACIENTE).answeredBy(actor).trim();

        boolean hasPatientName = !patientName.isBlank() && !NO_PATIENT_LABEL.equalsIgnoreCase(patientName);
        boolean noActiveAttention = NO_ACTIVE_ATTENTION_LABEL.equalsIgnoreCase(attentionStatus)
            || CALLED_PATIENT_PENDING_LABEL.equalsIgnoreCase(attentionStatus);

        return hasPatientName && !noActiveAttention;
    }
}
