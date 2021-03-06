package jgaliweather.nlg.nlg_generators;

import java.util.HashMap;
import java.util.StringTokenizer;
import jgaliweather.configuration.partition_reader.Partition;
import jgaliweather.configuration.template_reader.LabelSet;
import simplenlg.features.Feature;
import simplenlg.features.NumberAgreement;
import simplenlg.features.Tense;
import simplenlg.framework.CoordinatedPhraseElement;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGFactory;
import simplenlg.phrasespec.AdvPhraseSpec;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.PPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;

/*
    Implements a natural language text generator
    for the temporal cloud coverage linguistic
    description approach.
 */
public class SkyCoverageGeneratorLevel1 {

    private LabelSet coverage;
    private Partition period_partitions;
    private LabelSet period_labels;
    private String result_string;
    private SPhraseSpec text;
    private boolean second_approach;
    private NLGFactory nlgFactory;

    /*
        Initializes a SkyCoverageGeneratorLevel1 object

        :param template: A cloud coverage template
        :param cov_labels: A cloud coverage labelset
        :param period_partitions: A cloud coverage temporal partition set
        :param period_labels: A period labelset
        :param result_string: A cloud coverage linguistic description

        :return: A new SkyCoverageGeneratorLevel1 object
     */
    public SkyCoverageGeneratorLevel1(LabelSet coverage, Partition period_partitions, LabelSet period_labels, String result_string, NLGFactory nlgFactory) {
        this.coverage = coverage;
        this.period_partitions = period_partitions;
        this.period_labels = period_labels;
        this.result_string = result_string;
        this.text = null;
        this.second_approach = false;

        this.nlgFactory = nlgFactory;
    }

    /*
        Parses the intermediate linguistic descriptions
        and returns a single natural language text
        description

        :return: A natural language description of the
        cloud coverage variable forecast
     */
    public NLGElement parseAndGenerate() {

        StringTokenizer st = new StringTokenizer(result_string);
        if (st.countTokens() == 1) {
            alternative0();
        } else if (st.countTokens() == 3) {
            alternative1(st.nextToken(), st.nextToken(), st.nextToken());
        }

        if (!second_approach) {
            return text;
        } else {
            return null;
        }
    }

    private void alternative0() {
        second_approach = true;
    }

    private void alternative1(String token1, String token2, String token3) {
        int l_common = 0, aux = 0;
        HashMap<String, Integer> labels = new HashMap();
        labels.merge(token1, 1, Integer::sum);
        labels.merge(token2, 1, Integer::sum);
        labels.merge(token3, 1, Integer::sum);

        aux = labels.getOrDefault(token1, 0);
        l_common = 0;

        if (aux > labels.getOrDefault(token2, 0)) {
            aux = labels.getOrDefault(token2, 0);
            l_common = 1;
        }
        if (aux > labels.getOrDefault(token3, 0)) {
            l_common = 2;
        }

        if (labels.size() == 1) {

            second_approach = true;

        } else if (labels.size() == 2) {

            text = nlgFactory.createClause("there", "be");
            text.setFeature(Feature.TENSE, Tense.FUTURE);

            NPPhraseSpec state = nlgFactory.createNounPhrase("sky");
            state.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
            state.addModifier(nlgFactory.createAdjectivePhrase(coverage.getLabels().get(token1).getData()));
            text.setObject(state);

            CoordinatedPhraseElement period_list1 = nlgFactory.createCoordinatedPhrase();

            PPPhraseSpec period1 = nlgFactory.createPrepositionPhrase(period_labels.getLabels().get(period_partitions.getSets().get(0).getName()).getData());
            period_list1.addCoordinate(period1);

            if (l_common != 0) {
                int n_index = 1;
                if (l_common == 1) {
                    n_index = 2;
                }

                PPPhraseSpec period2 = nlgFactory.createPrepositionPhrase(period_labels.getLabels().get(period_partitions.getSets().get(n_index).getName()).getData());
                period_list1.addCoordinate(period2);
            }

            period_list1.addPostModifier(nlgFactory.createPrepositionPhrase("of", nlgFactory.createNounPhrase("the", "term")));
            text.addComplement(period_list1);

            AdvPhraseSpec conn = nlgFactory.createAdverbPhrase("although");
            conn.setFeature(Feature.APPOSITIVE, true);

            SPhraseSpec second_part = nlgFactory.createClause("they", "be");
            second_part.setFeature(Feature.TENSE, Tense.FUTURE);
            
            if(l_common == 0) {
                second_part.addComplement(nlgFactory.createAdjectivePhrase(coverage.getLabels().get(token2).getData()));
            } else {
                second_part.addComplement(nlgFactory.createAdjectivePhrase(coverage.getLabels().get(token3).getData()));
            }
            
            

            CoordinatedPhraseElement period_list2 = nlgFactory.createCoordinatedPhrase();

            if (l_common == 0) {
                PPPhraseSpec period3 = nlgFactory.createPrepositionPhrase(period_labels.getLabels().get(period_partitions.getSets().get(1).getName()).getData());
                period_list2.addCoordinate(period3);

                PPPhraseSpec period4 = nlgFactory.createPrepositionPhrase(period_labels.getLabels().get(period_partitions.getSets().get(2).getName()).getData());
                period_list2.addCoordinate(period4);
            } else {
                PPPhraseSpec period3 = nlgFactory.createPrepositionPhrase(period_labels.getLabels().get(period_partitions.getSets().get(l_common).getName()).getData());
                period_list2.addCoordinate(period3);
            }

            period_list2.addPostModifier(nlgFactory.createPrepositionPhrase("of", nlgFactory.createNounPhrase("the", "term")));

            second_part.addComplement(period_list2);

            conn.addPostModifier(second_part);

            text.addPostModifier(conn);

        } else {

            text = nlgFactory.createClause("there", "be");
            text.setFeature(Feature.TENSE, Tense.FUTURE);
            
            CoordinatedPhraseElement period_list = nlgFactory.createCoordinatedPhrase();

            NPPhraseSpec state1 = nlgFactory.createNounPhrase("sky");
            state1.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

            state1.addPreModifier(nlgFactory.createAdjectivePhrase(coverage.getLabels().get(token1).getData()));
            state1.addPostModifier(nlgFactory.createPrepositionPhrase(period_labels.getLabels().get(period_partitions.getSets().get(0).getName()).getData()));
            period_list.addCoordinate(state1);

            NPPhraseSpec state2 = nlgFactory.createNounPhrase("sky");
            state2.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

            state2.addPreModifier(nlgFactory.createAdjectivePhrase(coverage.getLabels().get(token2).getData()));
            state2.addPostModifier(nlgFactory.createPrepositionPhrase(period_labels.getLabels().get(period_partitions.getSets().get(1).getName()).getData()));
            period_list.addCoordinate(state2);

            NPPhraseSpec state3 = nlgFactory.createNounPhrase("sky");
            state3.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

            state3.addPreModifier(nlgFactory.createAdjectivePhrase(coverage.getLabels().get(token3).getData()));
            state3.addPostModifier(nlgFactory.createPrepositionPhrase(period_labels.getLabels().get(period_partitions.getSets().get(2).getName()).getData()));
            period_list.addCoordinate(state3);

            period_list.addPostModifier(nlgFactory.createPrepositionPhrase("of", nlgFactory.createNounPhrase("the", "term")));

            
            text.setObject(period_list);
        }
    }

}
