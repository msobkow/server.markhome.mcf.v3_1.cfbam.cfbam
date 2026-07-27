
// Description: Java JPA Factory interface for ClearSubDep2.

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

package server.markhome.mcf.v3_1.cfbam.cfbam;

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;

/*
 *	ICFBamClearSubDep2Factory interface for ClearSubDep2
 */
public interface ICFBamClearSubDep2Factory extends ICFBamProtClearSubDep2Factory
{

	/**
	 *	Allocate a ClearSubDep1Idx key over ClearSubDep2 instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamClearSubDep2ByClearSubDep1IdxKey newByClearSubDep1IdxKey();

	/**
	 *	Allocate a protected ClearSubDep1Idx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtClearSubDep2ByClearSubDep1IdxKey asProtected(ICFBamClearSubDep2ByClearSubDep1IdxKey src);

	/**
	 *	Allocate a public ClearSubDep1Idx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubClearSubDep2ByClearSubDep1IdxKey asPublic(ICFBamClearSubDep2ByClearSubDep1IdxKey src);

	/**
	 *	Allocate a UNameIdx key over ClearSubDep2 instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamClearSubDep2ByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtClearSubDep2ByUNameIdxKey asProtected(ICFBamClearSubDep2ByUNameIdxKey src);

	/**
	 *	Allocate a public UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubClearSubDep2ByUNameIdxKey asPublic(ICFBamClearSubDep2ByUNameIdxKey src);

	/**
	 *	Allocate a ClearSubDep2 interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamClearSubDep2 newRec();

	/**
	 *	Allocate a protected ClearSubDep2 interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtClearSubDep2 asProtected(ICFBamClearSubDep2 src);

	/**
	 *	Allocate a public ClearSubDep2 interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubClearSubDep2 asPublic(ICFBamClearSubDep2 src);

	/**
	 *	Allocate a ClearSubDep2 history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamClearSubDep2H newHRec();

	/**
	 *	Allocate a protected ClearSubDep2 history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtClearSubDep2H asProtected(ICFBamClearSubDep2H src);

	/**
	 *	Allocate a public ClearSubDep2 history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubClearSubDep2H asPublic(ICFBamClearSubDep2H src);

}
