
// Description: Java 25 Default Factory implementation for ClearSubDep2 buffers

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
 *	CFBamBuffClearSubDep2Factory implementation of ICFBamClearSubDep2Factory for ClearSubDep2
 */
public class CFBamBuffClearSubDep2DefaultFactory
	implements ICFBamClearSubDep2Factory
{
	public CFBamBuffClearSubDep2DefaultFactory() {
	}

	@Override
	public ICFBamClearSubDep2ByClearSubDep1IdxKey newByClearSubDep1IdxKey() {
		ICFBamClearSubDep2ByClearSubDep1IdxKey key =
			new CFBamBuffClearSubDep2ByClearSubDep1IdxKey();
		return( key );
	}

	public CFBamBuffClearSubDep2ByClearSubDep1IdxKey ensureByClearSubDep1IdxKey(ICFBamClearSubDep2ByClearSubDep1IdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffClearSubDep2ByClearSubDep1IdxKey) {
			return( (CFBamBuffClearSubDep2ByClearSubDep1IdxKey)key );
		}
		else {
			CFBamBuffClearSubDep2ByClearSubDep1IdxKey mapped = new CFBamBuffClearSubDep2ByClearSubDep1IdxKey();
			mapped.setRequiredClearSubDep1Id( key.getRequiredClearSubDep1Id() );
			return( mapped );
		}
	}

	@Override
	public ICFBamClearSubDep2ByUNameIdxKey newByUNameIdxKey() {
		ICFBamClearSubDep2ByUNameIdxKey key =
			new CFBamBuffClearSubDep2ByUNameIdxKey();
		return( key );
	}

	public CFBamBuffClearSubDep2ByUNameIdxKey ensureByUNameIdxKey(ICFBamClearSubDep2ByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffClearSubDep2ByUNameIdxKey) {
			return( (CFBamBuffClearSubDep2ByUNameIdxKey)key );
		}
		else {
			CFBamBuffClearSubDep2ByUNameIdxKey mapped = new CFBamBuffClearSubDep2ByUNameIdxKey();
			mapped.setRequiredClearSubDep1Id( key.getRequiredClearSubDep1Id() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

	@Override
	public ICFBamClearSubDep2 newRec() {
		ICFBamClearSubDep2 rec =
			new CFBamBuffClearSubDep2();
		return( rec );
	}

	public CFBamBuffClearSubDep2 ensureRec(ICFBamClearSubDep2 rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffClearSubDep2) {
			return( (CFBamBuffClearSubDep2)rec );
		}
		else {
			CFBamBuffClearSubDep2 mapped = new CFBamBuffClearSubDep2();
			mapped.set(rec);
			return( mapped );
		}
	}

	@Override
	public ICFBamClearSubDep2H newHRec() {
		ICFBamClearSubDep2H hrec =
			new CFBamBuffClearSubDep2H();
		return( hrec );
	}

	public CFBamBuffClearSubDep2H ensureHRec(ICFBamClearSubDep2H hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamBuffClearSubDep2H) {
			return( (CFBamBuffClearSubDep2H)hrec );
		}
		else {
			CFBamBuffClearSubDep2H mapped = new CFBamBuffClearSubDep2H();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
