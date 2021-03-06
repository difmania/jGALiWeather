package jgaliweather.nlg.precipitation_nlg;

import java.util.ArrayList;
import jgaliweather.configuration.template_reader.LabelSet;
import simplenlg.phrasespec.PPPhraseSpec;

/*
    Defines a precipitation episode, composed of a list
    of ordered labels, and an associated period
 */
public class PrecipitationEpisode {

    private PrecipitationPeriod duration;
    private String label;
    private ArrayList<PrecipitationNuance> nuances;

    public PrecipitationEpisode() {
        this.duration = null;
        this.label = null;
        this.nuances = new ArrayList();
    }

    public PrecipitationEpisode(PrecipitationPeriod duration, String label) {
        this.duration = duration;
        this.label = label;
        this.nuances = new ArrayList();
    }

    public PrecipitationPeriod getDuration() {
        return duration;
    }

    public void setDuration(PrecipitationPeriod duration) {
        this.duration = duration;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<PrecipitationNuance> getNuances() {
        return nuances;
    }

    public void setNuances(ArrayList<PrecipitationNuance> nuances) {
        this.nuances = nuances;
    }

    /*
        Converts this object into a natural language expression

        :param expression_template: A wind expression template
        :param label_template: A precipitation labelset
        :param day_template: A day expression template
        :param time_template: A time expression template
        :param mode: Period text conversion mode

        :return: A natural language expression of this object
     */
    public PPPhraseSpec toText(LabelSet label_template, LabelSet day_template, LabelSet time_template, String mode) {

        ArrayList<PPPhraseSpec> durations = duration.toText(day_template, time_template, mode);
        PPPhraseSpec episode = durations.get(0);
        for (int i = 1; i < durations.size(); i++) {
            episode.addPostModifier(durations.get(i));
            
        }
        
        if (label.equals("I") || label.equals("P")) {
            return episode;
            
        } else {
            episode.addPostModifier(" (" + label_template.getLabels().get(label).getData() + ")");
            return episode;
        }
    }
}
