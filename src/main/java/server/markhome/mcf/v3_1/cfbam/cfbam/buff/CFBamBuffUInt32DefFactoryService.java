
// Description: Java 25 Factory service implementation for UInt32Def buffers

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
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfbam.cfbamobj.*;

/*
 *	Java 25 Factory service implementation for UInt32Def buffers.
 */
@Service("cfbam31BuffUInt32DefFactoryService")
public class CFBamBuffUInt32DefFactoryService
	implements ICFBamUInt32DefFactory
{
	public CFBamBuffUInt32DefFactoryService() {
	}

	@Override
	public ICFBamUInt32Def newRec() {
		ICFBamUInt32Def rec =
			new CFBamBuffUInt32Def();
		return( rec );
	}

	public CFBamBuffUInt32Def ensureRec(ICFBamUInt32Def rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffUInt32Def) {
			return ((CFBamBuffUInt32Def)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFBamUInt32Def.CLASS_CODE: {
					CFBamBuffUInt32Def mapped = new CFBamBuffUInt32Def();
					mapped.set(rec);
					return(mapped); }
				case ICFBamUInt32Type.CLASS_CODE: {
					CFBamBuffUInt32Type mapped = new CFBamBuffUInt32Type();
					mapped.set((ICFBamUInt32Type)rec);
					return(mapped); }
				case ICFBamUInt32Col.CLASS_CODE: {
					CFBamBuffUInt32Col mapped = new CFBamBuffUInt32Col();
					mapped.set((ICFBamUInt32Col)rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamUInt32Def",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamUInt32Def");
			}
		}
	}

	@Override
	public ICFBamUInt32DefH newHRec() {
		ICFBamUInt32DefH hrec =
			new CFBamBuffUInt32DefH();
		return( hrec );
	}

	public CFBamBuffUInt32DefH ensureHRec(ICFBamUInt32DefH hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFBamBuffUInt32DefH) {
			return ((CFBamBuffUInt32DefH)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFBamUInt32Def.CLASS_CODE: {
					CFBamBuffUInt32DefH mapped = new CFBamBuffUInt32DefH();
					mapped.set(hrec);
					return(mapped); }
				case ICFBamUInt32Type.CLASS_CODE: {
					CFBamBuffUInt32TypeH mapped = new CFBamBuffUInt32TypeH();
					mapped.set((ICFBamUInt32TypeH)hrec);
					return(mapped); }
				case ICFBamUInt32Col.CLASS_CODE: {
					CFBamBuffUInt32ColH mapped = new CFBamBuffUInt32ColH();
					mapped.set((ICFBamUInt32ColH)hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamUInt32Def",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamUInt32Def");
			}
		}
	}
}
