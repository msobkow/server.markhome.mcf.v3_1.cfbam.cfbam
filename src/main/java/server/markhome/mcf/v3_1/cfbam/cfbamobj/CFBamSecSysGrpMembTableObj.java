// Description: Java 25 Table Object implementation for SecSysGrpMemb.

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

public class CFBamSecSysGrpMembTableObj
	implements ICFBamSecSysGrpMembTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> members;
	private Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> allSecSysGrpMemb;
	private Map< ICFSecSecSysGrpMembBySysGrpIdxKey,
		Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > > indexBySysGrpIdx;
	private Map< ICFSecSecSysGrpMembByUserIdxKey,
		Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > > indexByUserIdx;
	public static String TABLE_NAME = "SecSysGrpMemb";
	public static String TABLE_DBNAME = "secsysgrpmemb";

	public CFBamSecSysGrpMembTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj>();
		allSecSysGrpMemb = null;
		indexBySysGrpIdx = null;
		indexByUserIdx = null;
	}

	public CFBamSecSysGrpMembTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj>();
		allSecSysGrpMemb = null;
		indexBySysGrpIdx = null;
		indexByUserIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecSysGrpMembTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecSysGrpMembTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecSysGrpMembTableObj.getRuntimeClassCode() );
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
		allSecSysGrpMemb = null;
		indexBySysGrpIdx = null;
		indexByUserIdx = null;
		List<ICFSecSecSysGrpMembObj> toForget = new LinkedList<ICFSecSecSysGrpMembObj>();
		ICFSecSecSysGrpMembObj cur = null;
		Iterator<ICFSecSecSysGrpMembObj> iter = members.values().iterator();
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
	 *	CFBamSecSysGrpMembObj.
	 */
	@Override
	public ICFSecSecSysGrpMembObj newInstance() {
		ICFSecSecSysGrpMembObj inst = new CFBamSecSysGrpMembObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecSysGrpMembObj.
	 */
	@Override
	public ICFSecSecSysGrpMembEditObj newEditInstance( ICFSecSecSysGrpMembObj orig ) {
		ICFSecSecSysGrpMembEditObj edit = new CFBamSecSysGrpMembEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecSysGrpMembObj realiseSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj ) {
		ICFSecSecSysGrpMembObj obj = Obj;
		ICFSecSecSysGrpMembPKey pkey = obj.getPKey();
		ICFSecSecSysGrpMembObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecSysGrpMembObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexBySysGrpIdx != null ) {
				ICFSecSecSysGrpMembBySysGrpIdxKey keySysGrpIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
				keySysGrpIdx.setRequiredSecSysGrpId( keepObj.getRequiredSecSysGrpId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapSysGrpIdx = indexBySysGrpIdx.get( keySysGrpIdx );
				if( mapSysGrpIdx != null ) {
					mapSysGrpIdx.remove( keepObj.getPKey() );
					if( mapSysGrpIdx.size() <= 0 ) {
						indexBySysGrpIdx.remove( keySysGrpIdx );
					}
				}
			}

			if( indexByUserIdx != null ) {
				ICFSecSecSysGrpMembByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newByUserIdxKey();
				keyUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapUserIdx = indexByUserIdx.get( keyUserIdx );
				if( mapUserIdx != null ) {
					mapUserIdx.remove( keepObj.getPKey() );
					if( mapUserIdx.size() <= 0 ) {
						indexByUserIdx.remove( keyUserIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexBySysGrpIdx != null ) {
				ICFSecSecSysGrpMembBySysGrpIdxKey keySysGrpIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
				keySysGrpIdx.setRequiredSecSysGrpId( keepObj.getRequiredSecSysGrpId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapSysGrpIdx = indexBySysGrpIdx.get( keySysGrpIdx );
				if( mapSysGrpIdx != null ) {
					mapSysGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUserIdx != null ) {
				ICFSecSecSysGrpMembByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newByUserIdxKey();
				keyUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapUserIdx = indexByUserIdx.get( keyUserIdx );
				if( mapUserIdx != null ) {
					mapUserIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecSysGrpMemb != null ) {
				allSecSysGrpMemb.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecSysGrpMemb != null ) {
				allSecSysGrpMemb.put( keepObj.getPKey(), keepObj );
			}

			if( indexBySysGrpIdx != null ) {
				ICFSecSecSysGrpMembBySysGrpIdxKey keySysGrpIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
				keySysGrpIdx.setRequiredSecSysGrpId( keepObj.getRequiredSecSysGrpId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapSysGrpIdx = indexBySysGrpIdx.get( keySysGrpIdx );
				if( mapSysGrpIdx != null ) {
					mapSysGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUserIdx != null ) {
				ICFSecSecSysGrpMembByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newByUserIdxKey();
				keyUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapUserIdx = indexByUserIdx.get( keyUserIdx );
				if( mapUserIdx != null ) {
					mapUserIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecSysGrpMembObj createSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj ) {
		ICFSecSecSysGrpMembObj obj = Obj;
		ICFSecSecSysGrpMemb rec = obj.getSecSysGrpMembRec();
		schema.getCFSecBackingStore().getTableSecSysGrpMemb().createSecSysGrpMemb(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey ) {
		return( readSecSysGrpMemb( pkey, false ) );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey, boolean forceRead ) {
		ICFSecSecSysGrpMembObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecSysGrpMemb readRec = schema.getCFSecBackingStore().getTableSecSysGrpMemb().readDerivedByIdIdx( null,
						pkey.getRequiredSecSysGrpId(),
						pkey.getRequiredSecUserId() );
			if( readRec != null ) {
				obj = schema.getSecSysGrpMembTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecSysGrpMembObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMemb( CFLibDbKeyHash256 SecSysGrpId,
		CFLibDbKeyHash256 SecUserId ) {
		return( readSecSysGrpMemb( SecSysGrpId,
			SecUserId, false ) );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMemb( CFLibDbKeyHash256 SecSysGrpId,
		CFLibDbKeyHash256 SecUserId, boolean forceRead ) {
		ICFSecSecSysGrpMembObj obj = null;
		ICFSecSecSysGrpMemb readRec = schema.getCFSecBackingStore().getTableSecSysGrpMemb().readDerivedByIdIdx( null,
			SecSysGrpId,
			SecUserId );
		if( readRec != null ) {
				obj = schema.getSecSysGrpMembTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecSysGrpMembObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpMembObj readCachedSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey ) {
		ICFSecSecSysGrpMembObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecSysGrpMemb( ICFSecSecSysGrpMembObj obj )
	{
		final String S_ProcName = "CFBamSecSysGrpMembTableObj.reallyDeepDisposeSecSysGrpMemb() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecSysGrpMembPKey pkey = obj.getPKey();
		ICFSecSecSysGrpMembObj existing = readCachedSecSysGrpMemb( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecSysGrpMembBySysGrpIdxKey keySysGrpIdx = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
		keySysGrpIdx.setRequiredSecSysGrpId( existing.getRequiredSecSysGrpId() );

		ICFSecSecSysGrpMembByUserIdxKey keyUserIdx = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newByUserIdxKey();
		keyUserIdx.setRequiredSecUserId( existing.getRequiredSecUserId() );



		if( indexBySysGrpIdx != null ) {
			if( indexBySysGrpIdx.containsKey( keySysGrpIdx ) ) {
				indexBySysGrpIdx.get( keySysGrpIdx ).remove( pkey );
				if( indexBySysGrpIdx.get( keySysGrpIdx ).size() <= 0 ) {
					indexBySysGrpIdx.remove( keySysGrpIdx );
				}
			}
		}

		if( indexByUserIdx != null ) {
			if( indexByUserIdx.containsKey( keyUserIdx ) ) {
				indexByUserIdx.get( keyUserIdx ).remove( pkey );
				if( indexByUserIdx.get( keyUserIdx ).size() <= 0 ) {
					indexByUserIdx.remove( keyUserIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey ) {
		ICFSecSecSysGrpMembObj obj = readCachedSecSysGrpMemb( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecSysGrpMembObj lockSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey ) {
		ICFSecSecSysGrpMembObj locked = null;
		ICFSecSecSysGrpMemb lockRec = schema.getCFSecBackingStore().getTableSecSysGrpMemb().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecSysGrpMembTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecSysGrpMembObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecSysGrpMemb", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readAllSecSysGrpMemb() {
		return( readAllSecSysGrpMemb( false ) );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readAllSecSysGrpMemb( boolean forceRead ) {
		final String S_ProcName = "readAllSecSysGrpMemb";
		if( ( allSecSysGrpMemb == null ) || forceRead ) {
			Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> map = new HashMap<ICFSecSecSysGrpMembPKey,ICFSecSecSysGrpMembObj>();
			allSecSysGrpMemb = map;
			ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().readAllDerived( null );
			ICFSecSecSysGrpMemb rec;
			ICFSecSecSysGrpMembObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysGrpMembObj realised = (ICFSecSecSysGrpMembObj)obj.realise();
			}
		}
		int len = allSecSysGrpMemb.size();
		ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
		Iterator<ICFSecSecSysGrpMembObj> valIter = allSecSysGrpMemb.values().iterator();
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
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			@Override
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readCachedAllSecSysGrpMemb() {
		final String S_ProcName = "readCachedAllSecSysGrpMemb";
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>();
		if( allSecSysGrpMemb != null ) {
			int len = allSecSysGrpMemb.size();
			ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
			Iterator<ICFSecSecSysGrpMembObj> valIter = allSecSysGrpMemb.values().iterator();
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
		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecSysGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecSysGrpMembObj> pageAllSecSysGrpMemb(CFLibDbKeyHash256 priorSecSysGrpId,
		CFLibDbKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageAllSecSysGrpMemb";
		Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> map = new HashMap<ICFSecSecSysGrpMembPKey,ICFSecSecSysGrpMembObj>();
		ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().pageAllRec( null,
			priorSecSysGrpId,
			priorSecUserId );
		ICFSecSecSysGrpMemb rec;
		ICFSecSecSysGrpMembObj obj;
		ICFSecSecSysGrpMembObj realised;
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecSysGrpMembObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMembByIdIdx( CFLibDbKeyHash256 SecSysGrpId,
		CFLibDbKeyHash256 SecUserId )
	{
		return( readSecSysGrpMembByIdIdx( SecSysGrpId,
			SecUserId,
			false ) );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMembByIdIdx( CFLibDbKeyHash256 SecSysGrpId,
		CFLibDbKeyHash256 SecUserId, boolean forceRead )
	{
		ICFSecSecSysGrpMembPKey pkey = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newPKey();
		pkey.setRequiredContainerGroup(SecSysGrpId);
		pkey.setRequiredParentUser(SecUserId);
		ICFSecSecSysGrpMembObj obj = readSecSysGrpMemb( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readSecSysGrpMembBySysGrpIdx( CFLibDbKeyHash256 SecSysGrpId )
	{
		return( readSecSysGrpMembBySysGrpIdx( SecSysGrpId,
			false ) );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readSecSysGrpMembBySysGrpIdx( CFLibDbKeyHash256 SecSysGrpId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSysGrpMembBySysGrpIdx";
		ICFSecSecSysGrpMembBySysGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict;
		if( indexBySysGrpIdx == null ) {
			indexBySysGrpIdx = new HashMap< ICFSecSecSysGrpMembBySysGrpIdxKey,
				Map< ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > >();
		}
		if( ( ! forceRead ) && indexBySysGrpIdx.containsKey( key ) ) {
			dict = indexBySysGrpIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj>();
			ICFSecSecSysGrpMembObj obj;
			ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().readDerivedBySysGrpIdx( null,
				SecSysGrpId );
			ICFSecSecSysGrpMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSysGrpMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysGrpMembObj realised = (ICFSecSecSysGrpMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySysGrpIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
		Iterator<ICFSecSecSysGrpMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			@Override
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readSecSysGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId )
	{
		return( readSecSysGrpMembByUserIdx( SecUserId,
			false ) );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readSecSysGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSysGrpMembByUserIdx";
		ICFSecSecSysGrpMembByUserIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newByUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict;
		if( indexByUserIdx == null ) {
			indexByUserIdx = new HashMap< ICFSecSecSysGrpMembByUserIdxKey,
				Map< ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > >();
		}
		if( ( ! forceRead ) && indexByUserIdx.containsKey( key ) ) {
			dict = indexByUserIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj>();
			ICFSecSecSysGrpMembObj obj;
			ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().readDerivedByUserIdx( null,
				SecUserId );
			ICFSecSecSysGrpMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSysGrpMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysGrpMembObj realised = (ICFSecSecSysGrpMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByUserIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
		Iterator<ICFSecSecSysGrpMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			@Override
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecSysGrpMembObj readCachedSecSysGrpMembByIdIdx( CFLibDbKeyHash256 SecSysGrpId,
		CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecSysGrpMembObj obj = null;
		ICFSecSecSysGrpMembPKey pkey = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newPKey();
		pkey.setRequiredContainerGroup(SecSysGrpId);
		pkey.setRequiredParentUser(SecUserId);
		pkey.setRequiredContainerGroup(SecSysGrpId);
		pkey.setRequiredParentUser(SecUserId);
		obj = readCachedSecSysGrpMemb( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readCachedSecSysGrpMembBySysGrpIdx( CFLibDbKeyHash256 SecSysGrpId )
	{
		final String S_ProcName = "readCachedSecSysGrpMembBySysGrpIdx";
		ICFSecSecSysGrpMembBySysGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>();
		if( indexBySysGrpIdx != null ) {
			Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict;
			if( indexBySysGrpIdx.containsKey( key ) ) {
				dict = indexBySysGrpIdx.get( key );
				int len = dict.size();
				ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
				Iterator<ICFSecSecSysGrpMembObj> valIter = dict.values().iterator();
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
			ICFSecSecSysGrpMembObj obj;
			Iterator<ICFSecSecSysGrpMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			@Override
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readCachedSecSysGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId )
	{
		final String S_ProcName = "readCachedSecSysGrpMembByUserIdx";
		ICFSecSecSysGrpMembByUserIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newByUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>();
		if( indexByUserIdx != null ) {
			Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict;
			if( indexByUserIdx.containsKey( key ) ) {
				dict = indexByUserIdx.get( key );
				int len = dict.size();
				ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
				Iterator<ICFSecSecSysGrpMembObj> valIter = dict.values().iterator();
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
			ICFSecSecSysGrpMembObj obj;
			Iterator<ICFSecSecSysGrpMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			@Override
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecSysGrpMembByIdIdx( CFLibDbKeyHash256 SecSysGrpId,
		CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecSysGrpMembObj obj = readCachedSecSysGrpMembByIdIdx( SecSysGrpId,
				SecUserId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecSysGrpMembBySysGrpIdx( CFLibDbKeyHash256 SecSysGrpId )
	{
		final String S_ProcName = "deepDisposeSecSysGrpMembBySysGrpIdx";
		ICFSecSecSysGrpMembObj obj;
		List<ICFSecSecSysGrpMembObj> arrayList = readCachedSecSysGrpMembBySysGrpIdx( SecSysGrpId );
		if( arrayList != null )  {
			Iterator<ICFSecSecSysGrpMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecSysGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId )
	{
		final String S_ProcName = "deepDisposeSecSysGrpMembByUserIdx";
		ICFSecSecSysGrpMembObj obj;
		List<ICFSecSecSysGrpMembObj> arrayList = readCachedSecSysGrpMembByUserIdx( SecUserId );
		if( arrayList != null )  {
			Iterator<ICFSecSecSysGrpMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SysGrpIdx key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSysGrpMembObj> pageSecSysGrpMembBySysGrpIdx( CFLibDbKeyHash256 SecSysGrpId,
		CFLibDbKeyHash256 priorSecSysGrpId,
		CFLibDbKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageSecSysGrpMembBySysGrpIdx";
		ICFSecSecSysGrpMembBySysGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		List<ICFSecSecSysGrpMembObj> retList = new LinkedList<ICFSecSecSysGrpMembObj>();
		ICFSecSecSysGrpMembObj obj;
		ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().pageRecBySysGrpIdx( null,
				SecSysGrpId,
			priorSecSysGrpId,
			priorSecUserId );
		ICFSecSecSysGrpMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSysGrpMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSysGrpMembObj realised = (ICFSecSecSysGrpMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate UserIdx key attributes.
	 *
	 *	@param	SecUserId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSysGrpMembObj> pageSecSysGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId,
		CFLibDbKeyHash256 priorSecSysGrpId,
		CFLibDbKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageSecSysGrpMembByUserIdx";
		ICFSecSecSysGrpMembByUserIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newByUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		List<ICFSecSecSysGrpMembObj> retList = new LinkedList<ICFSecSecSysGrpMembObj>();
		ICFSecSecSysGrpMembObj obj;
		ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().pageRecByUserIdx( null,
				SecUserId,
			priorSecSysGrpId,
			priorSecUserId );
		ICFSecSecSysGrpMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSysGrpMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSysGrpMembObj realised = (ICFSecSecSysGrpMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecSysGrpMembObj updateSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj ) {
		ICFSecSecSysGrpMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysGrpMemb().updateSecSysGrpMemb( null,
			Obj.getSecSysGrpMembRec() );
		obj = (ICFSecSecSysGrpMembObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj ) {
		ICFSecSecSysGrpMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysGrpMemb().deleteSecSysGrpMemb( null,
			obj.getSecSysGrpMembRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecSysGrpMembByIdIdx( CFLibDbKeyHash256 SecSysGrpId,
		CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecSysGrpMembObj obj = readSecSysGrpMemb(SecSysGrpId,
				SecUserId);
		if( obj != null ) {
			ICFSecSecSysGrpMembEditObj editObj = (ICFSecSecSysGrpMembEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecSysGrpMembEditObj)obj.beginEdit();
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
		deepDisposeSecSysGrpMembByIdIdx( SecSysGrpId,
				SecUserId );
	}

	@Override
	public void deleteSecSysGrpMembBySysGrpIdx( CFLibDbKeyHash256 SecSysGrpId )
	{
		ICFSecSecSysGrpMembBySysGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		if( indexBySysGrpIdx == null ) {
			indexBySysGrpIdx = new HashMap< ICFSecSecSysGrpMembBySysGrpIdxKey,
				Map< ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > >();
		}
		if( indexBySysGrpIdx.containsKey( key ) ) {
			Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict = indexBySysGrpIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysGrpMemb().deleteSecSysGrpMembBySysGrpIdx( null,
				SecSysGrpId );
			Iterator<ICFSecSecSysGrpMembObj> iter = dict.values().iterator();
			ICFSecSecSysGrpMembObj obj;
			List<ICFSecSecSysGrpMembObj> toForget = new LinkedList<ICFSecSecSysGrpMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySysGrpIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecSysGrpMemb().deleteSecSysGrpMembBySysGrpIdx( null,
				SecSysGrpId );
		}
		deepDisposeSecSysGrpMembBySysGrpIdx( SecSysGrpId );
	}

	@Override
	public void deleteSecSysGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecSysGrpMembByUserIdxKey key = schema.getCFSecBackingStore().getFactorySecSysGrpMemb().newByUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		if( indexByUserIdx == null ) {
			indexByUserIdx = new HashMap< ICFSecSecSysGrpMembByUserIdxKey,
				Map< ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > >();
		}
		if( indexByUserIdx.containsKey( key ) ) {
			Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict = indexByUserIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysGrpMemb().deleteSecSysGrpMembByUserIdx( null,
				SecUserId );
			Iterator<ICFSecSecSysGrpMembObj> iter = dict.values().iterator();
			ICFSecSecSysGrpMembObj obj;
			List<ICFSecSecSysGrpMembObj> toForget = new LinkedList<ICFSecSecSysGrpMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByUserIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecSysGrpMemb().deleteSecSysGrpMembByUserIdx( null,
				SecUserId );
		}
		deepDisposeSecSysGrpMembByUserIdx( SecUserId );
	}
}