// Description: Java 25 Buffer Hooks for CFBam

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbam.buff;

import java.io.Serializable;
import java.math.*;
import java.net.InetAddress;
import java.time.*;
import java.util.*;
import jakarta.persistence.*;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.buff.*;
import server.markhome.mcf.v3_1.cfint.cfint.buff.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.buff.*;

/**
 *	Hooks for schema CFBam Spring resources that need to be used by getter-wrappers for AtomicReference members of the multi-threaded wedge between Spring resources and POJO code.
 *	This implementation wraps the Spring singletons instantiated during Spring's boot process for the server.markhome.mcf.v3_1.cfbam.cfbam.buff package.  It resolves all known standard Spring resources for the LocalContainerEntityManagerFactoryBean, SchemaService, IdGenService, and the CFBam*Repository and CFBam*Service singletons that the POJOs need to invoke.  However, it relies on late-initialization during dynamic instance creation (i.e. new CFBamBuffSchemaHooks()) instead of being initialized as a Spring singleton. As the Spring instance hierarchy is instantiated before any instances of this class are instantiated, the net result bridges the gap between POJO and Buffer.
 */
@Service("cfbam31BuffHooksSchema")
public class CFBamBuffHooksSchema {

//	@Autowired
//	@Qualifier("cfbam31BuffSchemaService")
//	private CFBamBuffSchemaService schemaService;

	@Autowired
	@Qualifier("cfbam31BuffFactory")
	private CFBamBuffFactoryService factoryService;

//	@Autowired
//	@Qualifier("CFBam31BuffIdGenService")
//	private CFBamBuffIdGenService idGenService;

	public CFBamBuffFactoryService getFactoryService() {
		if ( factoryService == null ) {
			// Dynamically resolve the factory by qualifier name
			throw new CFLibNotImplementedYetException( getClass(), "getFactoryService",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either",
				"ERROR - do not know how to dynamically resolve Spring beans from POJO code yet and AspectJ did not resolve it either" );
		}
		return( factoryService );
	}
}
