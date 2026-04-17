// Description: Java 25 Table Object implementation for SecSysGrp.

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

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public class CFBamSecSysGrpTableObj
	implements ICFBamSecSysGrpTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<CFLibDbKeyHash256, ICFSecSecSysGrpObj> members;
	private Map<CFLibDbKeyHash256, ICFSecSecSysGrpObj> allSecSysGrp;
	private Map< ICFSecSecSysGrpByUNameIdxKey,
		ICFSecSecSysGrpObj > indexByUNameIdx;
	private Map< ICFSecSecSysGrpBySecLevelIdxKey,
		Map<CFLibDbKeyHash256, ICFSecSecSysGrpObj > > indexBySecLevelIdx;
	public static String TABLE_NAME = "SecSysGrp";
	public static String TABLE_DBNAME = "secsysgrp";

	public CFBamSecSysGrpTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecSysGrpObj>();
		allSecSysGrp = null;
		indexByUNameIdx = null;
		indexBySecLevelIdx = null;
	}

	public CFBamSecSysGrpTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecSysGrpObj>();
		allSecSysGrp = null;
		indexByUNameIdx = null;
		indexBySecLevelIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecSysGrpTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecSysGrpTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecSysGrpTableObj.getRuntimeClassCode() );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( null );
	}


	@Override
	public void minimizeMemory() {
		allSecSysGrp = null;
		indexByUNameIdx = null;
		indexBySecLevelIdx = null;
		List<ICFSecSecSysGrpObj> toForget = new LinkedList<ICFSecSecSysGrpObj>();
		ICFSecSecSysGrpObj cur = null;
		Iterator<ICFSecSecSysGrpObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecSysGrpObj.
	 */
	@Override
	public ICFSecSecSysGrpObj newInstance() {
		ICFSecSecSysGrpObj inst = new CFBamSecSysGrpObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecSysGrpObj.
	 */
	@Override
	public ICFSecSecSysGrpEditObj newEditInstance( ICFSecSecSysGrpObj orig ) {
		ICFSecSecSysGrpEditObj edit = new CFBamSecSysGrpEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecSysGrpObj realiseSecSysGrp( ICFSecSecSysGrpObj Obj ) {
		ICFSecSecSysGrpObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecSysGrpObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecSysGrpObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFSecSecSysGrpByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrp().newByUNameIdxKey();
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexBySecLevelIdx != null ) {
				ICFSecSecSysGrpBySecLevelIdxKey keySecLevelIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrp().newBySecLevelIdxKey();
				keySecLevelIdx.setRequiredSecLevel( keepObj.getRequiredSecLevel() );
				Map<CFLibDbKeyHash256, ICFSecSecSysGrpObj > mapSecLevelIdx = indexBySecLevelIdx.get( keySecLevelIdx );
				if( mapSecLevelIdx != null ) {
					mapSecLevelIdx.remove( keepObj.getPKey() );
					if( mapSecLevelIdx.size() <= 0 ) {
						indexBySecLevelIdx.remove( keySecLevelIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFSecSecSysGrpByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrp().newByUNameIdxKey();
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexBySecLevelIdx != null ) {
				ICFSecSecSysGrpBySecLevelIdxKey keySecLevelIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrp().newBySecLevelIdxKey();
				keySecLevelIdx.setRequiredSecLevel( keepObj.getRequiredSecLevel() );
				Map<CFLibDbKeyHash256, ICFSecSecSysGrpObj > mapSecLevelIdx = indexBySecLevelIdx.get( keySecLevelIdx );
				if( mapSecLevelIdx != null ) {
					mapSecLevelIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecSysGrp != null ) {
				allSecSysGrp.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecSysGrp != null ) {
				allSecSysGrp.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecSysGrpByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrp().newByUNameIdxKey();
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexBySecLevelIdx != null ) {
				ICFSecSecSysGrpBySecLevelIdxKey keySecLevelIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrp().newBySecLevelIdxKey();
				keySecLevelIdx.setRequiredSecLevel( keepObj.getRequiredSecLevel() );
				Map<CFLibDbKeyHash256, ICFSecSecSysGrpObj > mapSecLevelIdx = indexBySecLevelIdx.get( keySecLevelIdx );
				if( mapSecLevelIdx != null ) {
					mapSecLevelIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecSysGrpObj createSecSysGrp( ICFSecSecSysGrpObj Obj ) {
		ICFSecSecSysGrpObj obj = Obj;
		ICFSecSecSysGrp rec = obj.getSecSysGrpRec();
		schema.getCFSecBackingStore().getTableSecSysGrp().createSecSysGrp(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpObj readSecSysGrp( CFLibDbKeyHash256 pkey ) {
		return( readSecSysGrp( pkey, false ) );
	}

	@Override
	public ICFSecSecSysGrpObj readSecSysGrp( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFSecSecSysGrpObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecSysGrp readRec = schema.getCFSecBackingStore().getTableSecSysGrp().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecSysGrpTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecSysGrpObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpObj readCachedSecSysGrp( CFLibDbKeyHash256 pkey ) {
		ICFSecSecSysGrpObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecSysGrp( ICFSecSecSysGrpObj obj )
	{
		final String S_ProcName = "CFBamSecSysGrpTableObj.reallyDeepDisposeSecSysGrp() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecSysGrpObj existing = readCachedSecSysGrp( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecSysGrpByUNameIdxKey keyUNameIdx = schema.getCFSecBackingStore().getFactorySecSysGrp().newByUNameIdxKey();
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFSecSecSysGrpBySecLevelIdxKey keySecLevelIdx = schema.getCFSecBackingStore().getFactorySecSysGrp().newBySecLevelIdxKey();
		keySecLevelIdx.setRequiredSecLevel( existing.getRequiredSecLevel() );


					schema.getSecSysGrpMembTableObj().deepDisposeSecSysGrpMembBySysGrpIdx( existing.getRequiredSecSysGrpId() );
					schema.getSecSysGrpIncTableObj().deepDisposeSecSysGrpIncBySysGrpIdx( existing.getRequiredSecSysGrpId() );

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexBySecLevelIdx != null ) {
			if( indexBySecLevelIdx.containsKey( keySecLevelIdx ) ) {
				indexBySecLevelIdx.get( keySecLevelIdx ).remove( pkey );
				if( indexBySecLevelIdx.get( keySecLevelIdx ).size() <= 0 ) {
					indexBySecLevelIdx.remove( keySecLevelIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecSysGrp( CFLibDbKeyHash256 pkey ) {
		ICFSecSecSysGrpObj obj = readCachedSecSysGrp( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecSysGrpObj lockSecSysGrp( CFLibDbKeyHash256 pkey ) {
		ICFSecSecSysGrpObj locked = null;
		ICFSecSecSysGrp lockRec = schema.getCFSecBackingStore().getTableSecSysGrp().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecSysGrpTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecSysGrpObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecSysGrp", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecSysGrpObj> readAllSecSysGrp() {
		return( readAllSecSysGrp( false ) );
	}

	@Override
	public List<ICFSecSecSysGrpObj> readAllSecSysGrp( boolean forceRead ) {
		final String S_ProcName = "readAllSecSysGrp";
		if( ( allSecSysGrp == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFSecSecSysGrpObj> map = new HashMap<CFLibDbKeyHash256,ICFSecSecSysGrpObj>();
			allSecSysGrp = map;
			ICFSecSecSysGrp[] recList = schema.getCFSecBackingStore().getTableSecSysGrp().readAllDerived( null );
			ICFSecSecSysGrp rec;
			ICFSecSecSysGrpObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysGrpObj realised = (ICFSecSecSysGrpObj)obj.realise();
			}
		}
		int len = allSecSysGrp.size();
		ICFSecSecSysGrpObj arr[] = new ICFSecSecSysGrpObj[len];
		Iterator<ICFSecSecSysGrpObj> valIter = allSecSysGrp.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecSysGrpObj> arrayList = new ArrayList<ICFSecSecSysGrpObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysGrpObj> cmp = new Comparator<ICFSecSecSysGrpObj>() {
			@Override
			public int compare( ICFSecSecSysGrpObj lhs, ICFSecSecSysGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysGrpObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysGrpObj> readCachedAllSecSysGrp() {
		final String S_ProcName = "readCachedAllSecSysGrp";
		ArrayList<ICFSecSecSysGrpObj> arrayList = new ArrayList<ICFSecSecSysGrpObj>();
		if( allSecSysGrp != null ) {
			int len = allSecSysGrp.size();
			ICFSecSecSysGrpObj arr[] = new ICFSecSecSysGrpObj[len];
			Iterator<ICFSecSecSysGrpObj> valIter = allSecSysGrp.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFSecSecSysGrpObj> cmp = new Comparator<ICFSecSecSysGrpObj>() {
			public int compare( ICFSecSecSysGrpObj lhs, ICFSecSecSysGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFSecSecSysGrpObj readSecSysGrpByIdIdx( CFLibDbKeyHash256 SecSysGrpId )
	{
		return( readSecSysGrpByIdIdx( SecSysGrpId,
			false ) );
	}

	@Override
	public ICFSecSecSysGrpObj readSecSysGrpByIdIdx( CFLibDbKeyHash256 SecSysGrpId, boolean forceRead )
	{
		ICFSecSecSysGrpObj obj = readSecSysGrp( SecSysGrpId, forceRead );
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpObj readSecSysGrpByUNameIdx( String Name )
	{
		return( readSecSysGrpByUNameIdx( Name,
			false ) );
	}

	@Override
	public ICFSecSecSysGrpObj readSecSysGrpByUNameIdx( String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecSysGrpByUNameIdxKey,
				ICFSecSecSysGrpObj >();
		}
		ICFSecSecSysGrpByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrp().newByUNameIdxKey();
		key.setRequiredName( Name );
		ICFSecSecSysGrpObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFSecSecSysGrp rec = schema.getCFSecBackingStore().getTableSecSysGrp().readDerivedByUNameIdx( null,
				Name );
			if( rec != null ) {
				obj = schema.getSecSysGrpTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecSysGrpObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFSecSecSysGrpObj> readSecSysGrpBySecLevelIdx( ICFSecSchema.SecLevelEnum SecLevel )
	{
		return( readSecSysGrpBySecLevelIdx( SecLevel,
			false ) );
	}

	@Override
	public List<ICFSecSecSysGrpObj> readSecSysGrpBySecLevelIdx( ICFSecSchema.SecLevelEnum SecLevel,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSysGrpBySecLevelIdx";
		ICFSecSecSysGrpBySecLevelIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrp().newBySecLevelIdxKey();
		key.setRequiredSecLevel( SecLevel );
		Map<CFLibDbKeyHash256, ICFSecSecSysGrpObj> dict;
		if( indexBySecLevelIdx == null ) {
			indexBySecLevelIdx = new HashMap< ICFSecSecSysGrpBySecLevelIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecSysGrpObj > >();
		}
		if( ( ! forceRead ) && indexBySecLevelIdx.containsKey( key ) ) {
			dict = indexBySecLevelIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFSecSecSysGrpObj>();
			ICFSecSecSysGrpObj obj;
			ICFSecSecSysGrp[] recList = schema.getCFSecBackingStore().getTableSecSysGrp().readDerivedBySecLevelIdx( null,
				SecLevel );
			ICFSecSecSysGrp rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSysGrpTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysGrpObj realised = (ICFSecSecSysGrpObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySecLevelIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSysGrpObj arr[] = new ICFSecSecSysGrpObj[len];
		Iterator<ICFSecSecSysGrpObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecSysGrpObj> arrayList = new ArrayList<ICFSecSecSysGrpObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysGrpObj> cmp = new Comparator<ICFSecSecSysGrpObj>() {
			@Override
			public int compare( ICFSecSecSysGrpObj lhs, ICFSecSecSysGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysGrpObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecSysGrpObj readCachedSecSysGrpByIdIdx( CFLibDbKeyHash256 SecSysGrpId )
	{
		ICFSecSecSysGrpObj obj = null;
		obj = readCachedSecSysGrp( SecSysGrpId );
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpObj readCachedSecSysGrpByUNameIdx( String Name )
	{
		ICFSecSecSysGrpObj obj = null;
		ICFSecSecSysGrpByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrp().newByUNameIdxKey();
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFSecSecSysGrpObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFSecSecSysGrpObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFSecSecSysGrpObj> readCachedSecSysGrpBySecLevelIdx( ICFSecSchema.SecLevelEnum SecLevel )
	{
		final String S_ProcName = "readCachedSecSysGrpBySecLevelIdx";
		ICFSecSecSysGrpBySecLevelIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrp().newBySecLevelIdxKey();
		key.setRequiredSecLevel( SecLevel );
		ArrayList<ICFSecSecSysGrpObj> arrayList = new ArrayList<ICFSecSecSysGrpObj>();
		if( indexBySecLevelIdx != null ) {
			Map<CFLibDbKeyHash256, ICFSecSecSysGrpObj> dict;
			if( indexBySecLevelIdx.containsKey( key ) ) {
				dict = indexBySecLevelIdx.get( key );
				int len = dict.size();
				ICFSecSecSysGrpObj arr[] = new ICFSecSecSysGrpObj[len];
				Iterator<ICFSecSecSysGrpObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecSysGrpObj obj;
			Iterator<ICFSecSecSysGrpObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSysGrpObj> cmp = new Comparator<ICFSecSecSysGrpObj>() {
			@Override
			public int compare( ICFSecSecSysGrpObj lhs, ICFSecSecSysGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecSysGrpByIdIdx( CFLibDbKeyHash256 SecSysGrpId )
	{
		ICFSecSecSysGrpObj obj = readCachedSecSysGrpByIdIdx( SecSysGrpId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecSysGrpByUNameIdx( String Name )
	{
		ICFSecSecSysGrpObj obj = readCachedSecSysGrpByUNameIdx( Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecSysGrpBySecLevelIdx( ICFSecSchema.SecLevelEnum SecLevel )
	{
		final String S_ProcName = "deepDisposeSecSysGrpBySecLevelIdx";
		ICFSecSecSysGrpObj obj;
		List<ICFSecSecSysGrpObj> arrayList = readCachedSecSysGrpBySecLevelIdx( SecLevel );
		if( arrayList != null )  {
			Iterator<ICFSecSecSysGrpObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFSecSecSysGrpObj updateSecSysGrp( ICFSecSecSysGrpObj Obj ) {
		ICFSecSecSysGrpObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysGrp().updateSecSysGrp( null,
			Obj.getSecSysGrpRec() );
		obj = (ICFSecSecSysGrpObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecSysGrp( ICFSecSecSysGrpObj Obj ) {
		ICFSecSecSysGrpObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysGrp().deleteSecSysGrp( null,
			obj.getSecSysGrpRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecSysGrpByIdIdx( CFLibDbKeyHash256 SecSysGrpId )
	{
		ICFSecSecSysGrpObj obj = readSecSysGrp(SecSysGrpId);
		if( obj != null ) {
			ICFSecSecSysGrpEditObj editObj = (ICFSecSecSysGrpEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecSysGrpEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeSecSysGrpByIdIdx( SecSysGrpId );
	}

	@Override
	public void deleteSecSysGrpByUNameIdx( String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecSysGrpByUNameIdxKey,
				ICFSecSecSysGrpObj >();
		}
		ICFSecSecSysGrpByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrp().newByUNameIdxKey();
		key.setRequiredName( Name );
		ICFSecSecSysGrpObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysGrp().deleteSecSysGrpByUNameIdx( null,
				Name );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecSysGrp().deleteSecSysGrpByUNameIdx( null,
				Name );
		}
		deepDisposeSecSysGrpByUNameIdx( Name );
	}

	@Override
	public void deleteSecSysGrpBySecLevelIdx( ICFSecSchema.SecLevelEnum SecLevel )
	{
		ICFSecSecSysGrpBySecLevelIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrp().newBySecLevelIdxKey();
		key.setRequiredSecLevel( SecLevel );
		if( indexBySecLevelIdx == null ) {
			indexBySecLevelIdx = new HashMap< ICFSecSecSysGrpBySecLevelIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecSysGrpObj > >();
		}
		if( indexBySecLevelIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFSecSecSysGrpObj> dict = indexBySecLevelIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysGrp().deleteSecSysGrpBySecLevelIdx( null,
				SecLevel );
			Iterator<ICFSecSecSysGrpObj> iter = dict.values().iterator();
			ICFSecSecSysGrpObj obj;
			List<ICFSecSecSysGrpObj> toForget = new LinkedList<ICFSecSecSysGrpObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySecLevelIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecSysGrp().deleteSecSysGrpBySecLevelIdx( null,
				SecLevel );
		}
		deepDisposeSecSysGrpBySecLevelIdx( SecLevel );
	}
}