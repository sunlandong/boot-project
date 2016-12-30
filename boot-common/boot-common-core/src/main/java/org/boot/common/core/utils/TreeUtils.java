package org.boot.common.core.utils;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TreeUtils {

	      
	    public static JSONArray getTree(JSONArray tree){      
	    	JSONArray result = new JSONArray();
	        JSONArray treeTemp = (JSONArray) tree.clone();  
	        for (Object x : tree) {    
	        	JSONObject node = (JSONObject)x;
	            if(StringUtils.isBlank(node.getString("vcParentId"))){  
	            	//treeTemp.remove(node);
	            	node.put("childrenNode", getChild(node.getString("vcId"),treeTemp)); 
	            	result.add(node);  
	            }  
	        }     
	        return result;  
	    }  
	    
	    /**
	     * 
	     * @param tree
	     * @param checkTree
	     * @param checkindex
	     * @param index
	     * @return
	     */
	    public static JSONArray getTreeWith(JSONArray tree,JSONArray checkTree,String checkindex,String index){      
	    	JSONArray result = new JSONArray();
	        JSONArray treeTemp = (JSONArray) tree.clone(); 
	        for(Object y : checkTree) {
	        	JSONObject nodecheck = (JSONObject)y;
	        	for (Object x : tree) {    
		        	JSONObject node = (JSONObject)x;
		        	if(StringUtils.equals(nodecheck.getString(checkindex), node.getString(index))){
		        		node.put("checked", true);
		        	}
		            if(StringUtils.isBlank(node.getString("vcParentId"))){  
		            	//treeTemp.remove(node);
		            	node.put("childrenNode", getChild(node.getString("vcId"),treeTemp)); 
		            	result.add(node);  
		            }  
		        }  
	        }
	           
	        return result;  
	    } 
	      
	      
	    public static JSONArray getChild(String id,JSONArray tree){  
	    	JSONArray children = new JSONArray();
	    	for(Object x : tree){
	    		JSONObject node = (JSONObject)x;
	    		if(StringUtils.equals(node.getString("vcParentId"), id)){
	    			//tree.remove(node);
	    			node.put("childrenNode", getChild(node.getString("vcId"),tree));
	    			children.add(node);
	    		}
	    	}
			return children;
	          
	    }  
	
}
