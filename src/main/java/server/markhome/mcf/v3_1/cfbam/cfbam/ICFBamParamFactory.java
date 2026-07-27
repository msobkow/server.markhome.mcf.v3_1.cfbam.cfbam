
// Description: Java JPA Factory interface for Param.

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
 *	ICFBamParamFactory interface for Param
 */
public interface ICFBamParamFactory extends ICFBamProtParamFactory
{

	/**
	 *	Allocate a primary history key for Param instances.
	 *
	 *	@return	The new instance.
	 */
	ICFBamParamHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for Param instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFBamProtParamHPKey asProtected(ICFBamParamHPKey src);

	/**
	 *	Allocate a public primary history key for Param instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFBamPubParamHPKey asPublic(ICFBamParamHPKey src);

	/**
	 *	Allocate a UNameIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtParamByUNameIdxKey asProtected(ICFBamParamByUNameIdxKey src);

	/**
	 *	Allocate a public UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubParamByUNameIdxKey asPublic(ICFBamParamByUNameIdxKey src);

	/**
	 *	Allocate a ServerMethodIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByServerMethodIdxKey newByServerMethodIdxKey();

	/**
	 *	Allocate a protected ServerMethodIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtParamByServerMethodIdxKey asProtected(ICFBamParamByServerMethodIdxKey src);

	/**
	 *	Allocate a public ServerMethodIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubParamByServerMethodIdxKey asPublic(ICFBamParamByServerMethodIdxKey src);

	/**
	 *	Allocate a DefSchemaIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByDefSchemaIdxKey newByDefSchemaIdxKey();

	/**
	 *	Allocate a protected DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtParamByDefSchemaIdxKey asProtected(ICFBamParamByDefSchemaIdxKey src);

	/**
	 *	Allocate a public DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubParamByDefSchemaIdxKey asPublic(ICFBamParamByDefSchemaIdxKey src);

	/**
	 *	Allocate a ServerTypeIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByServerTypeIdxKey newByServerTypeIdxKey();

	/**
	 *	Allocate a protected ServerTypeIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtParamByServerTypeIdxKey asProtected(ICFBamParamByServerTypeIdxKey src);

	/**
	 *	Allocate a public ServerTypeIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubParamByServerTypeIdxKey asPublic(ICFBamParamByServerTypeIdxKey src);

	/**
	 *	Allocate a PrevIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByPrevIdxKey newByPrevIdxKey();

	/**
	 *	Allocate a protected PrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtParamByPrevIdxKey asProtected(ICFBamParamByPrevIdxKey src);

	/**
	 *	Allocate a public PrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubParamByPrevIdxKey asPublic(ICFBamParamByPrevIdxKey src);

	/**
	 *	Allocate a NextIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByNextIdxKey newByNextIdxKey();

	/**
	 *	Allocate a protected NextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtParamByNextIdxKey asProtected(ICFBamParamByNextIdxKey src);

	/**
	 *	Allocate a public NextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubParamByNextIdxKey asPublic(ICFBamParamByNextIdxKey src);

	/**
	 *	Allocate a ContPrevIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByContPrevIdxKey newByContPrevIdxKey();

	/**
	 *	Allocate a protected ContPrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtParamByContPrevIdxKey asProtected(ICFBamParamByContPrevIdxKey src);

	/**
	 *	Allocate a public ContPrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubParamByContPrevIdxKey asPublic(ICFBamParamByContPrevIdxKey src);

	/**
	 *	Allocate a ContNextIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByContNextIdxKey newByContNextIdxKey();

	/**
	 *	Allocate a protected ContNextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtParamByContNextIdxKey asProtected(ICFBamParamByContNextIdxKey src);

	/**
	 *	Allocate a public ContNextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubParamByContNextIdxKey asPublic(ICFBamParamByContNextIdxKey src);

	/**
	 *	Allocate a Param interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParam newRec();

	/**
	 *	Allocate a protected Param interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtParam asProtected(ICFBamParam src);

	/**
	 *	Allocate a public Param interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubParam asPublic(ICFBamParam src);

	/**
	 *	Allocate a Param history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamH newHRec();

	/**
	 *	Allocate a protected Param history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtParamH asProtected(ICFBamParamH src);

	/**
	 *	Allocate a public Param history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubParamH asPublic(ICFBamParamH src);

}
