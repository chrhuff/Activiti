/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.workflow.simple.definition.form;

import java.util.ArrayList;
import java.util.List;

import org.activiti.workflow.simple.exception.SimpleWorkflowException;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * A form-property with a value that is represented as single item selected
 * from a list of possible values.
 *  
 * @author Frederik Heremans
 */
@JsonTypeName("list")
public class ListPropertyDefinition extends FormPropertyDefinition {

	protected List<ListPropertyEntry> entries = new ArrayList<ListPropertyEntry>();
	
	public void setEntries(List<ListPropertyEntry> entries) {
	  this.entries = entries;
  }
	
	@JsonSerialize(contentAs=ListPropertyEntry.class)
	public List<ListPropertyEntry> getEntries() {
	  return entries;
  }
	
	public void addEntry(ListPropertyEntry entry) {
		entries.add(entry);
	}
	
	@Override
	public FormPropertyDefinition clone() {
		ListPropertyDefinition clone = new ListPropertyDefinition();
		clone.setValues(this);
	  return clone;
	}
	
	@Override
	public void setValues(FormPropertyDefinition otherDefinition) {
		if(!(otherDefinition instanceof ListPropertyDefinition)) {
			throw new SimpleWorkflowException("An instance of ListPropertyDefinition is required to set values");
		}
		
		ListPropertyDefinition datePropertyDefinition = (ListPropertyDefinition) otherDefinition;
		setName(datePropertyDefinition.getName());
		setMandatory(datePropertyDefinition.isMandatory());
		setWritable(datePropertyDefinition.isWritable());
		
		// Copy the entries from the other definition
		if(entries == null) {
			entries = new ArrayList<ListPropertyEntry>();
		}
		if(datePropertyDefinition.getEntries() != null) {
			ListPropertyEntry newEntry = null;
			for(ListPropertyEntry entry : datePropertyDefinition.getEntries()) {
				newEntry = new ListPropertyEntry(entry.getValue(), entry.getName());
				entries.add(newEntry);
			}
		}
	}
}
