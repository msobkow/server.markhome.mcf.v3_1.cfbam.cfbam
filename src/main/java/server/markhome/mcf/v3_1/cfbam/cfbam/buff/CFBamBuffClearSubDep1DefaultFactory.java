
// Description: Java 25 Default Factory implementation for ClearSubDep1 buffers

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
 *	CFBamBuffClearSubDep1Factory implementation of ICFBamClearSubDep1Factory for ClearSubDep1
 */
public class CFBamBuffClearSubDep1DefaultFactory
	implements ICFBamClearSubDep1Factory
{
	public CFBamBuffClearSubDep1DefaultFactory() {
	}

	@Override
	public ICFBamClearSubDep1ByClearTopDepIdxKey newByClearTopDepIdxKey() {
		ICFBamClearSubDep1ByClearTopDepIdxKey key =
			new CFBamBuffClearSubDep1ByClearTopDepIdxKey();
		return( key );
	}

	public CFBamBuffClearSubDep1ByClearTopDepIdxKey ensureByClearTopDepIdxKey(ICFBamClearSubDep1ByClearTopDepIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffClearSubDep1ByClearTopDepIdxKey) {
			return( (CFBamBuffClearSubDep1ByClearTopDepIdxKey)key );
		}
		else {
			CFBamBuffClearSubDep1ByClearTopDepIdxKey mapped = new CFBamBuffClearSubDep1ByClearTopDepIdxKey();
			mapped.setRequiredClearTopDepId( key.getRequiredClearTopDepId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamClearSubDep1ByUNameIdxKey newByUNameIdxKey() {
		ICFBamClearSubDep1ByUNameIdxKey key =
			new CFBamBuffClearSubDep1ByUNameIdxKey();
		return( key );
	}

	public CFBamBuffClearSubDep1ByUNameIdxKey ensureByUNameIdxKey(ICFBamClearSubDep1ByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffClearSubDep1ByUNameIdxKey) {
			return( (CFBamBuffClearSubDep1ByUNameIdxKey)key );
		}
		else {
			CFBamBuffClearSubDep1ByUNameIdxKey mapped = new CFBamBuffClearSubDep1ByUNameIdxKey();
			mapped.setRequiredClearTopDepId( key.getRequiredClearTopDepId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

	@Override
	public ICFBamClearSubDep1 newRec() {
		ICFBamClearSubDep1 rec =
			new CFBamBuffClearSubDep1();
		return( rec );
	}

	public CFBamBuffClearSubDep1 ensureRec(ICFBamClearSubDep1 rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffClearSubDep1) {
			return( (CFBamBuffClearSubDep1)rec );
		}
		else {
			CFBamBuffClearSubDep1 mapped = new CFBamBuffClearSubDep1();
			mapped.set(rec);
			return( mapped );
		}
	}

	@Override
	public ICFBamClearSubDep1H newHRec() {
		ICFBamClearSubDep1H hrec =
			new CFBamBuffClearSubDep1H();
		return( hrec );
	}

	public CFBamBuffClearSubDep1H ensureHRec(ICFBamClearSubDep1H hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamBuffClearSubDep1H) {
			return( (CFBamBuffClearSubDep1H)hrec );
		}
		else {
			CFBamBuffClearSubDep1H mapped = new CFBamBuffClearSubDep1H();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
