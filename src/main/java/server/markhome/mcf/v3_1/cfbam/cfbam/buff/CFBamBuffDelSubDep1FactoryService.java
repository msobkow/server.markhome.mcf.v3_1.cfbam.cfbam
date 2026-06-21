
// Description: Java 25 Factory service implementation for DelSubDep1 buffers

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
 *	Java 25 Factory service implementation for DelSubDep1 buffers.
 */
@Service("cfbam31BuffDelSubDep1FactoryService")
public class CFBamBuffDelSubDep1FactoryService
	implements ICFBamDelSubDep1Factory
{
	public CFBamBuffDelSubDep1FactoryService() {
	}

	@Override
	public ICFBamDelSubDep1ByDelTopDepIdxKey newByDelTopDepIdxKey() {
		ICFBamDelSubDep1ByDelTopDepIdxKey key =
			new CFBamBuffDelSubDep1ByDelTopDepIdxKey();
		return( key );
	}

	public CFBamBuffDelSubDep1ByDelTopDepIdxKey ensureByDelTopDepIdxKey(ICFBamDelSubDep1ByDelTopDepIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffDelSubDep1ByDelTopDepIdxKey) {
			return( (CFBamBuffDelSubDep1ByDelTopDepIdxKey)key );
		}
		else {
			CFBamBuffDelSubDep1ByDelTopDepIdxKey mapped = new CFBamBuffDelSubDep1ByDelTopDepIdxKey();
			mapped.setRequiredDelTopDepId( key.getRequiredDelTopDepId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamDelSubDep1ByUNameIdxKey newByUNameIdxKey() {
		ICFBamDelSubDep1ByUNameIdxKey key =
			new CFBamBuffDelSubDep1ByUNameIdxKey();
		return( key );
	}

	public CFBamBuffDelSubDep1ByUNameIdxKey ensureByUNameIdxKey(ICFBamDelSubDep1ByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffDelSubDep1ByUNameIdxKey) {
			return( (CFBamBuffDelSubDep1ByUNameIdxKey)key );
		}
		else {
			CFBamBuffDelSubDep1ByUNameIdxKey mapped = new CFBamBuffDelSubDep1ByUNameIdxKey();
			mapped.setRequiredDelTopDepId( key.getRequiredDelTopDepId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

	@Override
	public ICFBamDelSubDep1 newRec() {
		ICFBamDelSubDep1 rec =
			new CFBamBuffDelSubDep1();
		return( rec );
	}

	public CFBamBuffDelSubDep1 ensureRec(ICFBamDelSubDep1 rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffDelSubDep1) {
			return ((CFBamBuffDelSubDep1)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFBamDelSubDep1.CLASS_CODE: {
					CFBamBuffDelSubDep1 mapped = new CFBamBuffDelSubDep1();
					mapped.set(rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamDelSubDep1",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamDelSubDep1");
			}
		}
	}

	@Override
	public ICFBamDelSubDep1H newHRec() {
		ICFBamDelSubDep1H hrec =
			new CFBamBuffDelSubDep1H();
		return( hrec );
	}

	public CFBamBuffDelSubDep1H ensureHRec(ICFBamDelSubDep1H hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFBamBuffDelSubDep1H) {
			return ((CFBamBuffDelSubDep1H)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFBamDelSubDep1.CLASS_CODE: {
					CFBamBuffDelSubDep1H mapped = new CFBamBuffDelSubDep1H();
					mapped.set(hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamDelSubDep1",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamDelSubDep1");
			}
		}
	}
}
