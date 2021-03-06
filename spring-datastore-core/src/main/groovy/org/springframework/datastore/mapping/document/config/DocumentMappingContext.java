/* Copyright (C) 2010 SpringSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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

package org.springframework.datastore.mapping.document.config;

import org.springframework.datastore.mapping.model.AbstractMappingContext;
import org.springframework.datastore.mapping.model.MappingConfigurationStrategy;
import org.springframework.datastore.mapping.model.MappingContext;
import org.springframework.datastore.mapping.model.MappingFactory;
import org.springframework.datastore.mapping.model.PersistentEntity;
import org.springframework.datastore.mapping.model.config.GormMappingConfigurationStrategy;

/**
 * Models a {@link MappingContext} for a Document store
 * 
 * @author Graeme Rocher
 *
 */
public class DocumentMappingContext extends AbstractMappingContext{
    String defaultDatabaseName;
	MappingFactory<Collection, Attribute> mappingFactory;

	private MappingConfigurationStrategy syntaxStrategy;

	public DocumentMappingContext(String defaultDatabaseName) {
		super();
        if(defaultDatabaseName == null) throw new IllegalArgumentException("Argument [defaultDatabaseName] cannot be null");
		this.defaultDatabaseName = defaultDatabaseName;
		this.mappingFactory = createDocumentMappingFactory();
		this.syntaxStrategy = new GormMappingConfigurationStrategy(mappingFactory);
	}

	protected MappingFactory createDocumentMappingFactory() {
		return new GormDocumentMappingFactory();
	}
	
	public String getDefaultDatabaseName() {
		return defaultDatabaseName;
	}

	@Override
	public MappingConfigurationStrategy getMappingSyntaxStrategy() {
		return syntaxStrategy;
	}

	@Override
	public MappingFactory<Collection, Attribute> getMappingFactory() {
		return mappingFactory;
	}

	@Override
	protected PersistentEntity createPersistentEntity(Class javaClass) {
		return new DocumentPersistentEntity(javaClass, this);
	}

}
