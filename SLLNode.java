/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

/**
 *
 * @author Shubham
 */
public class SLLNode {
    int value;
    SLLNode next;
    
    public SLLNode(int i, SLLNode n){ //passess a value and the next node
        value = i;
        next = n;
    }
}
