
// Description: Java JPA Factory interface for Relation.

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
 *	ICFBamRelationFactory interface for Relation
 */
public interface ICFBamRelationFactory extends ICFBamProtRelationFactory
{

	/**
	 *	Allocate a UNameIdx key over Relation instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationByUNameIdxKey asProtected(ICFBamRelationByUNameIdxKey src);

	/**
	 *	Allocate a public UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationByUNameIdxKey asPublic(ICFBamRelationByUNameIdxKey src);

	/**
	 *	Allocate a RelTableIdx key over Relation instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationByRelTableIdxKey newByRelTableIdxKey();

	/**
	 *	Allocate a protected RelTableIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationByRelTableIdxKey asProtected(ICFBamRelationByRelTableIdxKey src);

	/**
	 *	Allocate a public RelTableIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationByRelTableIdxKey asPublic(ICFBamRelationByRelTableIdxKey src);

	/**
	 *	Allocate a RelCodeVisIdx key over Relation instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationByRelCodeVisIdxKey newByRelCodeVisIdxKey();

	/**
	 *	Allocate a protected RelCodeVisIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationByRelCodeVisIdxKey asProtected(ICFBamRelationByRelCodeVisIdxKey src);

	/**
	 *	Allocate a public RelCodeVisIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationByRelCodeVisIdxKey asPublic(ICFBamRelationByRelCodeVisIdxKey src);

	/**
	 *	Allocate a RelTableCodeVisX key over Relation instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationByRelTableCodeVisXKey newByRelTableCodeVisXKey();

	/**
	 *	Allocate a protected RelTableCodeVisX key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationByRelTableCodeVisXKey asProtected(ICFBamRelationByRelTableCodeVisXKey src);

	/**
	 *	Allocate a public RelTableCodeVisX key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationByRelTableCodeVisXKey asPublic(ICFBamRelationByRelTableCodeVisXKey src);

	/**
	 *	Allocate a DefSchemaIdx key over Relation instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationByDefSchemaIdxKey newByDefSchemaIdxKey();

	/**
	 *	Allocate a protected DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationByDefSchemaIdxKey asProtected(ICFBamRelationByDefSchemaIdxKey src);

	/**
	 *	Allocate a public DefSchemaIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationByDefSchemaIdxKey asPublic(ICFBamRelationByDefSchemaIdxKey src);

	/**
	 *	Allocate a FromKeyIdx key over Relation instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationByFromKeyIdxKey newByFromKeyIdxKey();

	/**
	 *	Allocate a protected FromKeyIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationByFromKeyIdxKey asProtected(ICFBamRelationByFromKeyIdxKey src);

	/**
	 *	Allocate a public FromKeyIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationByFromKeyIdxKey asPublic(ICFBamRelationByFromKeyIdxKey src);

	/**
	 *	Allocate a ToTblIdx key over Relation instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationByToTblIdxKey newByToTblIdxKey();

	/**
	 *	Allocate a protected ToTblIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationByToTblIdxKey asProtected(ICFBamRelationByToTblIdxKey src);

	/**
	 *	Allocate a public ToTblIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationByToTblIdxKey asPublic(ICFBamRelationByToTblIdxKey src);

	/**
	 *	Allocate a ToKeyIdx key over Relation instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationByToKeyIdxKey newByToKeyIdxKey();

	/**
	 *	Allocate a protected ToKeyIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationByToKeyIdxKey asProtected(ICFBamRelationByToKeyIdxKey src);

	/**
	 *	Allocate a public ToKeyIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationByToKeyIdxKey asPublic(ICFBamRelationByToKeyIdxKey src);

	/**
	 *	Allocate a NarrowedIdx key over Relation instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationByNarrowedIdxKey newByNarrowedIdxKey();

	/**
	 *	Allocate a protected NarrowedIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationByNarrowedIdxKey asProtected(ICFBamRelationByNarrowedIdxKey src);

	/**
	 *	Allocate a public NarrowedIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationByNarrowedIdxKey asPublic(ICFBamRelationByNarrowedIdxKey src);

	/**
	 *	Allocate a Relation interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelation newRec();

	/**
	 *	Allocate a protected Relation interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelation asProtected(ICFBamRelation src);

	/**
	 *	Allocate a public Relation interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelation asPublic(ICFBamRelation src);

	/**
	 *	Allocate a Relation history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationH newHRec();

	/**
	 *	Allocate a protected Relation history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamProtRelationH asProtected(ICFBamRelationH src);

	/**
	 *	Allocate a public Relation history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamPubRelationH asPublic(ICFBamRelationH src);

}
