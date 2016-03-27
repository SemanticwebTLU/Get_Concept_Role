/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MDataProcessing.Assertion;

import MCommon.Global;
import MDataProcessing.Individual.ConceptIndividuals;
import org.semanticweb.owlapi.model.IRI;

/**
 *
 * @author tdminh
 */
public class ConceptAssertion 
{
    private IRI iriConcept;
    private ConceptIndividuals individuals;
    
    public ConceptAssertion(IRI iriConcept, ConceptIndividuals individuals)
    {
        this.iriConcept = iriConcept;
        this.individuals = individuals;
    }
    
    public IRI getIRIConcept()
    {
        return this.iriConcept;
    }
    
    public String getConceptName()
    {
        return Global.cutNameOfIRI(this.iriConcept.toString());        
    }
    
    public ConceptIndividuals getIndividuals()
    {
        return this.individuals;
    }
}
