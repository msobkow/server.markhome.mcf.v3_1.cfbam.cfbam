
// Description: Java 25 Factory service implementation for DbKeyHash384Def buffers

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
 *	Java 25 Factory service implementation for DbKeyHash384Def buffers.
 */
@Service("cfbam31BuffDbKeyHash384DefFactoryService")
public class CFBamBuffDbKeyHash384DefFactoryService
	implements ICFBamDbKeyHash384DefFactory
{
	public CFBamBuffDbKeyHash384DefFactoryService() {
	}

	@Override
	public ICFBamDbKeyHash384Def newRec() {
		ICFBamDbKeyHash384Def rec =
			new CFBamBuffDbKeyHash384Def();
		return( rec );
	}

	public CFBamBuffDbKeyHash384Def ensureRec(ICFBamDbKeyHash384Def rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffDbKeyHash384Def) {
			return ((CFBamBuffDbKeyHash384Def)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFBamDbKeyHash384Def.CLASS_CODE: {
					CFBamBuffDbKeyHash384Def mapped = new CFBamBuffDbKeyHash384Def();
					mapped.set(rec);
					return(mapped); }
				case ICFBamDbKeyHash384Col.CLASS_CODE: {
					CFBamBuffDbKeyHash384Col mapped = new CFBamBuffDbKeyHash384Col();
					mapped.set((ICFBamDbKeyHash384Col)rec);
					return(mapped); }
				case ICFBamDbKeyHash384Type.CLASS_CODE: {
					CFBamBuffDbKeyHash384Type mapped = new CFBamBuffDbKeyHash384Type();
					mapped.set((ICFBamDbKeyHash384Type)rec);
					return(mapped); }
				case ICFBamDbKeyHash384Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash384Gen mapped = new CFBamBuffDbKeyHash384Gen();
					mapped.set((ICFBamDbKeyHash384Gen)rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamDbKeyHash384Def",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamDbKeyHash384Def");
			}
		}
	}

	@Override
	public ICFBamDbKeyHash384DefH newHRec() {
		ICFBamDbKeyHash384DefH hrec =
			new CFBamBuffDbKeyHash384DefH();
		return( hrec );
	}

	public CFBamBuffDbKeyHash384DefH ensureHRec(ICFBamDbKeyHash384DefH hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFBamBuffDbKeyHash384DefH) {
			return ((CFBamBuffDbKeyHash384DefH)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFBamDbKeyHash384Def.CLASS_CODE: {
					CFBamBuffDbKeyHash384DefH mapped = new CFBamBuffDbKeyHash384DefH();
					mapped.set(hrec);
					return(mapped); }
				case ICFBamDbKeyHash384Col.CLASS_CODE: {
					CFBamBuffDbKeyHash384ColH mapped = new CFBamBuffDbKeyHash384ColH();
					mapped.set((ICFBamDbKeyHash384ColH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash384Type.CLASS_CODE: {
					CFBamBuffDbKeyHash384TypeH mapped = new CFBamBuffDbKeyHash384TypeH();
					mapped.set((ICFBamDbKeyHash384TypeH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash384Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash384GenH mapped = new CFBamBuffDbKeyHash384GenH();
					mapped.set((ICFBamDbKeyHash384GenH)hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamDbKeyHash384Def",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamDbKeyHash384Def");
			}
		}
	}
}
