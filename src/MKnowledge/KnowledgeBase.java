package MKnowledge;


import org.semanticweb.HermiT.Reasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.vocab.PrefixOWLOntologyFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tdminh
 */
public class KnowledgeBase 
{
    //OWLOntologyManager manages a set of ontologies. It is the main 
    // point for creating, loading and accessing ontologies.
    private OWLOntologyManager ontologyManager;
    
    //An interface for creating entities, class expressions and axioms.
    private OWLDataFactory dataFactory;	
    
    /*
        An OWLOntology consists of a possibly empty set of OWLAxioms and a possibly empty 
        set of OWLAnnotations. An ontology can have an ontology IRI which can be used to identify the ontology. 
        If it has an ontology IRI then it may also have an ontology version IRI. Since OWL 2, an ontology need not have an ontology IRI. 
        (See the OWL 2 Structural Specification).
    */
    private OWLOntology ontology;
    private IRI iri;
    private PelletReasoner reasonerPellet;
    
    private PrefixOWLOntologyFormat prefix;
    
    public KnowledgeBase(IRI iri)
    {
        try 
        {
            this.iri = iri;
            this.ontologyManager = OWLManager.createOWLOntologyManager();
            this.dataFactory = this.ontologyManager.getOWLDataFactory();
            this.ontology = this.ontologyManager.loadOntologyFromOntologyDocument(iri);
            this.reasonerPellet = PelletReasonerFactory.getInstance().createReasoner(this.ontology);
            
            this.prefix = (PrefixOWLOntologyFormat) this.ontologyManager.getOntologyFormat(this.ontology); 
            //this.prefix.setDefaultPrefix(Global.BASE_URL); 
        } 
        catch (OWLOntologyCreationException e) 
        {	
            e.printStackTrace();
	}
    }
    
    public boolean isConsistent()
    {
        return reasonerPellet.isConsistent();
    }
    
    public OWLOntologyManager getOntologyManager()
    {
        return this.ontologyManager;
    }
    
    public OWLDataFactory getDataFactory()
    {
        return this.dataFactory;
    }
    
    public OWLOntology getOntology()
    {
        return this.ontology;
    }
    
    public IRI getIRI()
    {
        return this.iri;
    }
    
    public PelletReasoner getPelletReasoner()
    {
    	return this.reasonerPellet;
    }
    
    public PrefixOWLOntologyFormat getPrefix()
    {
        return this.prefix;
    }
    

}
