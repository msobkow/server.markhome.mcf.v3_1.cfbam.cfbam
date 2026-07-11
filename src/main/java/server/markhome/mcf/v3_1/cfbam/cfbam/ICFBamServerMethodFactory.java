
// Description: Java JPA Factory interface for ServerMethod.

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
 *	ICFBamServerMethodFactory interface for ServerMethod
 */
public interface ICFBamServerMethodFactory extends ICFBamProtServerMethodFactory
{

	/**
	 *	Allocate a UNameIdx key over ServerMethod instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamServerMethodByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtServerMethodByUNameIdxKey asProtected(ICFBamServerMethodByUNameIdxKey src);

	/**
	 *	Allocate a public UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubServerMethodByUNameIdxKey asPublic(ICFBamServerMethodByUNameIdxKey src);

	/**
	 *	Allocate a MethTableIdx key over ServerMethod instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamServerMethodByMethTableIdxKey newByMethTableIdxKey();

	/**
	 *	Allocate a protected MethTableIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtServerMethodByMethTableIdxKey asProtected(ICFBamServerMethodByMethTableIdxKey src);

	/**
	 *	Allocate a public MethTableIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubServerMethodByMethTableIdxKey asPublic(ICFBamServerMethodByMethTableIdxKey src);

	/**
	 *	Allocate a MethCodeVisIdx key over ServerMethod instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamServerMethodByMethCodeVisIdxKey newByMethCodeVisIdxKey();

	/**
	 *	Allocate a protected MethCodeVisIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtServerMethodByMethCodeVisIdxKey asProtected(ICFBamServerMethodByMethCodeVisIdxKey src);

	/**
	 *	Allocate a public MethCodeVisIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubServerMethodByMethCodeVisIdxKey asPublic(ICFBamServerMethodByMethCodeVisIdxKey src);

	/**
	 *	Allocate a MethTableVisIdx key over ServerMethod instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamServerMethodByMethTableVisIdxKey newByMethTableVisIdxKey();

	/**
	 *	Allocate a protected MethTableVisIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtServerMethodByMethTableVisIdxKey asProtected(ICFBamServerMethodByMethTableVisIdxKey src);

	/**
	 *	Allocate a public MethTableVisIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubServerMethodByMethTableVisIdxKey asPublic(ICFBamServerMethodByMethTableVisIdxKey src);

	/**
	 *	Allocate a DefSchemaIdx key over ServerMethod instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamServerMethodByDefSchemaIdxKey newByDefSchemaIdxKey();

	/**
	 *	Allocate a protected DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtServerMethodByDefSchemaIdxKey asProtected(ICFBamServerMethodByDefSchemaIdxKey src);

	/**
	 *	Allocate a public DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubServerMethodByDefSchemaIdxKey asPublic(ICFBamServerMethodByDefSchemaIdxKey src);

	/**
	 *	Allocate a ServerMethod interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamServerMethod newRec();

	/**
	 *	Allocate a protected ServerMethod interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtServerMethod asProtected(ICFBamServerMethod src);

	/**
	 *	Allocate a public ServerMethod interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubServerMethod asPublic(ICFBamServerMethod src);

	/**
	 *	Allocate a ServerMethod history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamServerMethodH newHRec();

	/**
	 *	Allocate a protected ServerMethod history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtServerMethodH asProtected(ICFBamServerMethodH src);

	/**
	 *	Allocate a public ServerMethod history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubServerMethodH asPublic(ICFBamServerMethodH src);

}
