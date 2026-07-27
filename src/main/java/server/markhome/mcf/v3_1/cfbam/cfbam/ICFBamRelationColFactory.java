
// Description: Java JPA Factory interface for RelationCol.

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
 *	ICFBamRelationColFactory interface for RelationCol
 */
public interface ICFBamRelationColFactory extends ICFBamProtRelationColFactory
{

	/**
	 *	Allocate a primary history key for RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	ICFBamRelationColHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for RelationCol instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFBamProtRelationColHPKey asProtected(ICFBamRelationColHPKey src);

	/**
	 *	Allocate a public primary history key for RelationCol instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFBamPubRelationColHPKey asPublic(ICFBamRelationColHPKey src);

	/**
	 *	Allocate a UNameIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationColByUNameIdxKey asProtected(ICFBamRelationColByUNameIdxKey src);

	/**
	 *	Allocate a public UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationColByUNameIdxKey asPublic(ICFBamRelationColByUNameIdxKey src);

	/**
	 *	Allocate a RelationIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByRelationIdxKey newByRelationIdxKey();

	/**
	 *	Allocate a protected RelationIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationColByRelationIdxKey asProtected(ICFBamRelationColByRelationIdxKey src);

	/**
	 *	Allocate a public RelationIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationColByRelationIdxKey asPublic(ICFBamRelationColByRelationIdxKey src);

	/**
	 *	Allocate a DefSchemaIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByDefSchemaIdxKey newByDefSchemaIdxKey();

	/**
	 *	Allocate a protected DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationColByDefSchemaIdxKey asProtected(ICFBamRelationColByDefSchemaIdxKey src);

	/**
	 *	Allocate a public DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationColByDefSchemaIdxKey asPublic(ICFBamRelationColByDefSchemaIdxKey src);

	/**
	 *	Allocate a FromColIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByFromColIdxKey newByFromColIdxKey();

	/**
	 *	Allocate a protected FromColIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationColByFromColIdxKey asProtected(ICFBamRelationColByFromColIdxKey src);

	/**
	 *	Allocate a public FromColIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationColByFromColIdxKey asPublic(ICFBamRelationColByFromColIdxKey src);

	/**
	 *	Allocate a ToColIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByToColIdxKey newByToColIdxKey();

	/**
	 *	Allocate a protected ToColIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationColByToColIdxKey asProtected(ICFBamRelationColByToColIdxKey src);

	/**
	 *	Allocate a public ToColIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationColByToColIdxKey asPublic(ICFBamRelationColByToColIdxKey src);

	/**
	 *	Allocate a PrevIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByPrevIdxKey newByPrevIdxKey();

	/**
	 *	Allocate a protected PrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationColByPrevIdxKey asProtected(ICFBamRelationColByPrevIdxKey src);

	/**
	 *	Allocate a public PrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationColByPrevIdxKey asPublic(ICFBamRelationColByPrevIdxKey src);

	/**
	 *	Allocate a NextIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByNextIdxKey newByNextIdxKey();

	/**
	 *	Allocate a protected NextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationColByNextIdxKey asProtected(ICFBamRelationColByNextIdxKey src);

	/**
	 *	Allocate a public NextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationColByNextIdxKey asPublic(ICFBamRelationColByNextIdxKey src);

	/**
	 *	Allocate a RelPrevIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByRelPrevIdxKey newByRelPrevIdxKey();

	/**
	 *	Allocate a protected RelPrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationColByRelPrevIdxKey asProtected(ICFBamRelationColByRelPrevIdxKey src);

	/**
	 *	Allocate a public RelPrevIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationColByRelPrevIdxKey asPublic(ICFBamRelationColByRelPrevIdxKey src);

	/**
	 *	Allocate a RelNextIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByRelNextIdxKey newByRelNextIdxKey();

	/**
	 *	Allocate a protected RelNextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationColByRelNextIdxKey asProtected(ICFBamRelationColByRelNextIdxKey src);

	/**
	 *	Allocate a public RelNextIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationColByRelNextIdxKey asPublic(ICFBamRelationColByRelNextIdxKey src);

	/**
	 *	Allocate a RelationCol interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationCol newRec();

	/**
	 *	Allocate a protected RelationCol interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationCol asProtected(ICFBamRelationCol src);

	/**
	 *	Allocate a public RelationCol interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationCol asPublic(ICFBamRelationCol src);

	/**
	 *	Allocate a RelationCol history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColH newHRec();

	/**
	 *	Allocate a protected RelationCol history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationColH asProtected(ICFBamRelationColH src);

	/**
	 *	Allocate a public RelationCol history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationColH asPublic(ICFBamRelationColH src);

}
