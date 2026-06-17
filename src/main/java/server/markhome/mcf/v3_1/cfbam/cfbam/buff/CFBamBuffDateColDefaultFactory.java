
// Description: Java 25 Default Factory implementation for DateCol buffers

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
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.buff.*;
import server.markhome.mcf.v3_1.cfint.cfint.buff.*;

/*
 *	CFBamBuffDateColFactory implementation of ICFBamDateColFactory for DateCol
 */
public class CFBamBuffDateColDefaultFactory
	implements ICFBamDateColFactory
{
	public CFBamBuffDateColDefaultFactory() {
	}

	@Override
	public ICFBamDateColByTableIdxKey newByTableIdxKey() {
		ICFBamDateColByTableIdxKey key =
			new CFBamBuffDateColByTableIdxKey();
		return( key );
	}

	public CFBamBuffDateColByTableIdxKey ensureByTableIdxKey(ICFBamDateColByTableIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffDateColByTableIdxKey) {
			return( (CFBamBuffDateColByTableIdxKey)key );
		}
		else {
			CFBamBuffDateColByTableIdxKey mapped = new CFBamBuffDateColByTableIdxKey();
			mapped.setRequiredTableId( key.getRequiredTableId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamDateCol newRec() {
		ICFBamDateCol rec =
			new CFBamBuffDateCol();
		return( rec );
	}

	public CFBamBuffDateCol ensureRec(ICFBamDateCol rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffDateCol) {
			return( (CFBamBuffDateCol)rec );
		}
		else {
			CFBamBuffDateCol mapped = new CFBamBuffDateCol();
			mapped.set(rec);
			return( mapped );
		}
	}

	@Override
	public ICFBamDateColH newHRec() {
		ICFBamDateColH hrec =
			new CFBamBuffDateColH();
		return( hrec );
	}

	public CFBamBuffDateColH ensureHRec(ICFBamDateColH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamBuffDateColH) {
			return( (CFBamBuffDateColH)hrec );
		}
		else {
			CFBamBuffDateColH mapped = new CFBamBuffDateColH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
