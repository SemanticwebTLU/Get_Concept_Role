/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MDataProcessing;

import MCommon.Global;
import MDataProcessing.Assertion.ConceptAssertion;
import MDataProcessing.Individual.ConceptIndividuals;
import MKnowledge.KnowledgeBase;
import java.util.ArrayList;
import java.util.Set;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

/**
 *
 * @author tdminh
 */
public class ConceptProcessing 
{
    //Getting all of concepts that has assertions greater than or equal iNumberOfMinimumAssertions
    //These concepts is put into the static variable ConceptProcessing.allFrequentConceptsFull
    public static void createFrequentConceptsForFullVersion(KnowledgeBase knowledgeBase)
    {
        Global.allFrequentConceptsFull = new ArrayList<ConceptAssertion>();
        
        Set<OWLClass> classes = knowledgeBase.getOntology().getClassesInSignature(); 
        classes.remove(knowledgeBase.getDataFactory().getOWLThing());
        
        for (OWLClass owlClass : classes)
        {
            Set<OWLNamedIndividual> setIndividuals = knowledgeBase.getPelletReasoner().getInstances(owlClass, false).getFlattened();
            
                ConceptIndividuals conceptIndividuals = new ConceptIndividuals();                
                for (OWLNamedIndividual individual : setIndividuals)
                    conceptIndividuals.addIndividual(Global.cutNameOfIRI(individual.getIRI().toString()));
                
                ConceptAssertion conceptAssertion = new ConceptAssertion(owlClass.getIRI(), conceptIndividuals);
                Global.allFrequentConceptsFull.add(conceptAssertion);
           
        }
    }
    
    //Get assertions of a concept that is respesented by iriConcept
    public static ConceptAssertion getConceptAssertionFromFrequentConceptsFull(IRI iriConcept)
    {
        for(ConceptAssertion ca : Global.allFrequentConceptsFull)        
            if (ca.getIRIConcept().equals(iriConcept))
                return ca;        
        return null;        
    } 
    //Get assertions of a concept that is respesented by iriConcept
    public static ConceptAssertion getConceptAssertionFromFrequentConceptsFull(String strConceptName)
    {
        for(ConceptAssertion ca : Global.allFrequentConceptsFull)        
            if (Global.cutNameOfIRI(ca.getIRIConcept().toString()).equals(strConceptName))
               return ca; 
        return null;        
    }
}
