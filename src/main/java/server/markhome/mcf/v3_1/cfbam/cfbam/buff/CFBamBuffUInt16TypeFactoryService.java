
// Description: Java 25 Factory service implementation for UInt16Type buffers

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

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.buff.*;
import server.markhome.mcf.v3_1.cfint.cfint.buff.*;

/*
 *	Java 25 Factory service implementation for UInt16Type buffers.
 */
@Service("cfbam31BuffUInt16TypeFactoryService")
public class CFBamBuffUInt16TypeFactoryService
	implements ICFBamUInt16TypeFactory
{
	public CFBamBuffUInt16TypeFactoryService() {
	}

	@Override
	public ICFBamUInt16TypeBySchemaIdxKey newBySchemaIdxKey() {
		ICFBamUInt16TypeBySchemaIdxKey key =
			new CFBamBuffUInt16TypeBySchemaIdxKey();
		return( key );
	}

	public CFBamBuffUInt16TypeBySchemaIdxKey ensureBySchemaIdxKey(ICFBamUInt16TypeBySchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffUInt16TypeBySchemaIdxKey) {
			return( (CFBamBuffUInt16TypeBySchemaIdxKey)key );
		}
		else {
			CFBamBuffUInt16TypeBySchemaIdxKey mapped = new CFBamBuffUInt16TypeBySchemaIdxKey();
			mapped.setRequiredSchemaDefId( key.getRequiredSchemaDefId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamUInt16Type newRec() {
		ICFBamUInt16Type rec =
			new CFBamBuffUInt16Type();
		return( rec );
	}

	public CFBamBuffUInt16Type ensureRec(ICFBamUInt16Type rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffUInt16Type) {
			return ((CFBamBuffUInt16Type)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFBamUInt16Type.CLASS_CODE: {
					CFBamBuffUInt16Type mapped = new CFBamBuffUInt16Type();
					mapped.set(rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamUInt16Type",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamUInt16Type");
			}
		}
	}

	@Override
	public ICFBamUInt16TypeH newHRec() {
		ICFBamUInt16TypeH hrec =
			new CFBamBuffUInt16TypeH();
		return( hrec );
	}

	public CFBamBuffUInt16TypeH ensureHRec(ICFBamUInt16TypeH hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFBamBuffUInt16TypeH) {
			return ((CFBamBuffUInt16TypeH)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFBamUInt16Type.CLASS_CODE: {
					CFBamBuffUInt16TypeH mapped = new CFBamBuffUInt16TypeH();
					mapped.set(hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamUInt16Type",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamUInt16Type");
			}
		}
	}
}
