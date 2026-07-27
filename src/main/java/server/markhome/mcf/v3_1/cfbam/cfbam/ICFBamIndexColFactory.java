
// Description: Java JPA Factory interface for IndexCol.

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
 *	ICFBamIndexColFactory interface for IndexCol
 */
public interface ICFBamIndexColFactory extends ICFBamProtIndexColFactory
{

	/**
	 *	Allocate a primary history key for IndexCol instances.
	 *
	 *	@return	The new instance.
	 */
	ICFBamIndexColHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for IndexCol instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFBamProtIndexColHPKey asProtected(ICFBamIndexColHPKey src);

	/**
	 *	Allocate a public primary history key for IndexCol instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFBamPubIndexColHPKey asPublic(ICFBamIndexColHPKey src);

	/**
	 *	Allocate a UNameIdx key over IndexCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamIndexColByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtIndexColByUNameIdxKey asProtected(ICFBamIndexColByUNameIdxKey src);

	/**
	 *	Allocate a public UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubIndexColByUNameIdxKey asPublic(ICFBamIndexColByUNameIdxKey src);

	/**
	 *	Allocate a IndexIdx key over IndexCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamIndexColByIndexIdxKey newByIndexIdxKey();

	/**
	 *	Allocate a protected IndexIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtIndexColByIndexIdxKey asProtected(ICFBamIndexColByIndexIdxKey src);

	/**
	 *	Allocate a public IndexIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubIndexColByIndexIdxKey asPublic(ICFBamIndexColByIndexIdxKey src);

	/**
	 *	Allocate a DefSchemaIdx key over IndexCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamIndexColByDefSchemaIdxKey newByDefSchemaIdxKey();

	/**
	 *	Allocate a protected DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtIndexColByDefSchemaIdxKey asProtected(ICFBamIndexColByDefSchemaIdxKey src);

	/**
	 *	Allocate a public DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubIndexColByDefSchemaIdxKey asPublic(ICFBamIndexColByDefSchemaIdxKey src);

	/**
	 *	Allocate a ColIdx key over IndexCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamIndexColByColIdxKey newByColIdxKey();

	/**
	 *	Allocate a protected ColIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtIndexColByColIdxKey asProtected(ICFBamIndexColByColIdxKey src);

	/**
	 *	Allocate a public ColIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubIndexColByColIdxKey asPublic(ICFBamIndexColByColIdxKey src);

	/**
	 *	Allocate a PrevIdx key over IndexCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamIndexColByPrevIdxKey newByPrevIdxKey();

	/**
	 *	Allocate a protected PrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtIndexColByPrevIdxKey asProtected(ICFBamIndexColByPrevIdxKey src);

	/**
	 *	Allocate a public PrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubIndexColByPrevIdxKey asPublic(ICFBamIndexColByPrevIdxKey src);

	/**
	 *	Allocate a NextIdx key over IndexCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamIndexColByNextIdxKey newByNextIdxKey();

	/**
	 *	Allocate a protected NextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtIndexColByNextIdxKey asProtected(ICFBamIndexColByNextIdxKey src);

	/**
	 *	Allocate a public NextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubIndexColByNextIdxKey asPublic(ICFBamIndexColByNextIdxKey src);

	/**
	 *	Allocate a IdxPrevIdx key over IndexCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamIndexColByIdxPrevIdxKey newByIdxPrevIdxKey();

	/**
	 *	Allocate a protected IdxPrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtIndexColByIdxPrevIdxKey asProtected(ICFBamIndexColByIdxPrevIdxKey src);

	/**
	 *	Allocate a public IdxPrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubIndexColByIdxPrevIdxKey asPublic(ICFBamIndexColByIdxPrevIdxKey src);

	/**
	 *	Allocate a IdxNextIdx key over IndexCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamIndexColByIdxNextIdxKey newByIdxNextIdxKey();

	/**
	 *	Allocate a protected IdxNextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtIndexColByIdxNextIdxKey asProtected(ICFBamIndexColByIdxNextIdxKey src);

	/**
	 *	Allocate a public IdxNextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubIndexColByIdxNextIdxKey asPublic(ICFBamIndexColByIdxNextIdxKey src);

	/**
	 *	Allocate a IndexCol interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamIndexCol newRec();

	/**
	 *	Allocate a protected IndexCol interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtIndexCol asProtected(ICFBamIndexCol src);

	/**
	 *	Allocate a public IndexCol interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubIndexCol asPublic(ICFBamIndexCol src);

	/**
	 *	Allocate a IndexCol history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamIndexColH newHRec();

	/**
	 *	Allocate a protected IndexCol history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtIndexColH asProtected(ICFBamIndexColH src);

	/**
	 *	Allocate a public IndexCol history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubIndexColH asPublic(ICFBamIndexColH src);

}
