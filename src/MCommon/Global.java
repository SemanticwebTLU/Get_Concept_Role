package MCommon;

import MDataProcessing.Assertion.ConceptAssertion;
import MDataProcessing.Assertion.RoleAssertion;
import MKnowledge.KnowledgeBase;
//import MRandom.MyRandom;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import org.semanticweb.owlapi.model.IRI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tdminh
 */
public class Global 
{
    public static IRI iriFileInput;
    public static KnowledgeBase knowledgeBase;
    public static ArrayList<ConceptAssertion> allFrequentConceptsFull;
    public static ArrayList<RoleAssertion> allFrequentRolesFull;
    public static ArrayList<RoleAssertion> allFrequentRoles;
    
    public static String cutNameOfIRI(String str)
    {
        String[] parts = str.split("#");		
        return parts[1]; 
    }
}
