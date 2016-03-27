/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. */
package MDataProcessing;

import MCommon.Global;
import MDataProcessing.Assertion.RoleAssertion;
import MDataProcessing.Individual.RoleIndividual;
import MDataProcessing.Individual.RoleIndividuals;
import MKnowledge.KnowledgeBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 *
 * @author tdminh
 */
public class RoleProcessing {

    //Getting all of roles that has assertions greater than or equal iNumberOfMinimumAssertions
    //These roles is put into the static variable RoleProcessing.allFrequentRolesFull    
    public static void createFrequentRolesForFullVersion(KnowledgeBase knowledgeBase) {
        Global.allFrequentRolesFull = new ArrayList<RoleAssertion>();

        Set<OWLObjectProperty> objectProperties = knowledgeBase.getOntology().getObjectPropertiesInSignature();

        for (OWLObjectProperty owlObjectProperty : objectProperties) {
            //Get all of class that are in Domain of ObjectProperty            
            Set<OWLClass> setDomainClass = knowledgeBase.getPelletReasoner().getObjectPropertyDomains(owlObjectProperty, true).getFlattened();

            int countAssertion = 0;
            boolean acceptAddToFrequentRole = false;

            //Checking if the number of assetions is greater than or equals iNumberOfMinimumAssertions
            RoleIndividuals roleIndividuals = new RoleIndividuals();

            for (OWLClass domainClass : setDomainClass) {
                //Get all of Individuals of each class of domain
                Set<OWLNamedIndividual> setDomainIndividuals = knowledgeBase.getPelletReasoner().getInstances(domainClass, false).getFlattened();

                for (OWLNamedIndividual domainIndividual : setDomainIndividuals) {
                    //Get all of range individuals corresponde with domain individuals  
                    Set<OWLNamedIndividual> setRangeIndividuals = knowledgeBase.getPelletReasoner().getObjectPropertyValues(domainIndividual, owlObjectProperty).getFlattened();

                    if (setRangeIndividuals.size() > 0) {
                        for (OWLNamedIndividual rangeIndividual : setRangeIndividuals) {
                            String strDomainIndividual = Global.cutNameOfIRI(domainIndividual.getIRI().toString());
                            String strRangeIndividual = Global.cutNameOfIRI(rangeIndividual.getIRI().toString());

                            RoleIndividual roleIndividual = new RoleIndividual(strDomainIndividual, strRangeIndividual);
                            roleIndividuals.addIndividual(roleIndividual);
                            roleIndividuals.addIndividual(strDomainIndividual, strRangeIndividual);
                        }
                    }
                }
            }

            RoleAssertion roleAssertion = new RoleAssertion(owlObjectProperty.getIRI(), roleIndividuals);
            Global.allFrequentRolesFull.add(roleAssertion);

        }
    }

    //Get assertions of a role that is respesented by iriRole
    public static RoleAssertion getRoleAssertionFromFrequentRolesFull(IRI iriRole) {
        for (RoleAssertion ra : Global.allFrequentRolesFull) {
            if (ra.getIRIRole().equals(iriRole)) {
                return ra;
            }
        }
        return null;
    }

    //Get assertions of a role that is respesented by Role name
    public static RoleAssertion getRoleAssertionFromFrequentRoles(String srtRoleName) {
        for (RoleAssertion ra : Global.allFrequentRolesFull) {
            if (ra.getRoleName().equals(srtRoleName)) {
                return ra;
            }
        }
        return null;
    }
}
